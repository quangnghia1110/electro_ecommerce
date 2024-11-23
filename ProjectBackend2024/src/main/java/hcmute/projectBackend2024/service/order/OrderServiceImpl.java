package hcmute.projectBackend2024.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hcmute.projectBackend2024.config.payment.PayPalHttpClient;
import hcmute.projectBackend2024.constant.AppConstants;
import hcmute.projectBackend2024.constant.FieldName;
import hcmute.projectBackend2024.constant.ResourceName;
import hcmute.projectBackend2024.dto.client.ClientConfirmedOrderResponse;
import hcmute.projectBackend2024.dto.client.ClientSimpleOrderRequest;
import hcmute.projectBackend2024.dto.payment.OrderIntent;
import hcmute.projectBackend2024.dto.payment.OrderStatus;
import hcmute.projectBackend2024.dto.payment.PaymentLandingPage;
import hcmute.projectBackend2024.dto.payment.PaypalRequest;
import hcmute.projectBackend2024.dto.payment.PaypalResponse;

import hcmute.projectBackend2024.entity.authentication.User;
import hcmute.projectBackend2024.entity.cart.Cart;
import hcmute.projectBackend2024.entity.cashbook.PaymentMethodType;
import hcmute.projectBackend2024.entity.general.Notification;
import hcmute.projectBackend2024.entity.general.NotificationType;
import hcmute.projectBackend2024.entity.order.Order;
import hcmute.projectBackend2024.entity.order.OrderResource;
import hcmute.projectBackend2024.entity.variant.OrderVariant;
import hcmute.projectBackend2024.exception.ResourceNotFoundException;
import hcmute.projectBackend2024.mapper.client.ClientOrderMapper;
import hcmute.projectBackend2024.mapper.general.NotificationMapper;
import hcmute.projectBackend2024.repository.authentication.UserRepository;
import hcmute.projectBackend2024.repository.cart.CartRepository;
import hcmute.projectBackend2024.repository.general.NotificationRepository;
import hcmute.projectBackend2024.repository.order.OrderRepository;

import hcmute.projectBackend2024.service.general.NotificationService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Value("${electro.app.shipping.ghnToken}")
    private String ghnToken;
    @Value("${electro.app.shipping.ghnShopId}")
    private String ghnShopId;
    @Value("${electro.app.shipping.ghnApiPath}")
    private String ghnApiPath;

    private final OrderRepository orderRepository;
   
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    private final PayPalHttpClient payPalHttpClient;
    private final ClientOrderMapper clientOrderMapper;

    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    private static final int USD_VND_RATE = 23_000;

    @Override
    public void cancelOrder(String code) {
        Order order = orderRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ORDER, FieldName.ORDER_CODE, code));

        // Hủy đơn hàng khi status = 1 hoặc 2
        if (order.getStatus() < 3) {
            order.setStatus(5); // Status 5 là trạng thái Hủy
            orderRepository.save(order);

            
        } else {
            throw new RuntimeException(String
                    .format("Order with code %s is in delivery or has been cancelled. Please check again!", code));
        }
    }

    @Override
    public ClientConfirmedOrderResponse createClientOrder(ClientSimpleOrderRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        Cart cart = cartRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.CART, FieldName.USERNAME, username));

        // (1) Tạo đơn hàng
        Order order = new Order();

        order.setCode(RandomString.make(12).toUpperCase());
        order.setStatus(1); // Status 1: Đơn hàng mới
        order.setToName(user.getFullname());
        order.setToPhone(user.getPhone());
        order.setToAddress(user.getAddress().getLine());
        order.setToWardName(user.getAddress().getWard().getName());
        order.setToDistrictName(user.getAddress().getDistrict().getName());
        order.setToProvinceName(user.getAddress().getProvince().getName());
        order.setOrderResource((OrderResource) new OrderResource().setId(1L)); // Default OrderResource
        order.setUser(user);

        order.setOrderVariants(cart.getCartVariants().stream()
                .map(cartVariant -> {
                   

                    double currentPrice = calculateDiscountedPrice(cartVariant.getVariant().getPrice());

                    return new OrderVariant()
                            .setOrder(order)
                            .setVariant(cartVariant.getVariant())
                            .setPrice(BigDecimal.valueOf(currentPrice))
                            .setQuantity(cartVariant.getQuantity())
                            .setAmount(BigDecimal.valueOf(currentPrice).multiply(BigDecimal.valueOf(cartVariant.getQuantity())));
                })
                .collect(Collectors.toSet()));

        // Calculate price values
        // TODO: Vấn đề khuyến mãi
        BigDecimal totalAmount = BigDecimal.valueOf(order.getOrderVariants().stream()
                .mapToDouble(orderVariant -> orderVariant.getAmount().doubleValue())
                .sum());

        BigDecimal tax = BigDecimal.valueOf(AppConstants.DEFAULT_TAX);

        BigDecimal shippingCost = BigDecimal.ZERO;

        BigDecimal totalPay = totalAmount
                .add(totalAmount.multiply(tax).setScale(0, RoundingMode.HALF_UP))
                .add(shippingCost);

        order.setTotalAmount(totalAmount);
        order.setTax(tax);
        order.setShippingCost(shippingCost);
        order.setTotalPay(totalPay);
        order.setPaymentMethodType(request.getPaymentMethodType());
        order.setPaymentStatus(1); // Status 1: Chưa thanh toán

        // (2) Tạo response
        ClientConfirmedOrderResponse response = new ClientConfirmedOrderResponse();

        response.setOrderCode(order.getCode());
        response.setOrderPaymentMethodType(order.getPaymentMethodType());

        // (3) Kiểm tra hình thức thanh toán
        if (request.getPaymentMethodType() == PaymentMethodType.CASH) {
            orderRepository.save(order);
        } else if (request.getPaymentMethodType() == PaymentMethodType.PAYPAL) {
            try {
                // (3.2.1) Tính tổng tiền theo USD
                BigDecimal totalPayUSD = order.getTotalPay()
                        .divide(BigDecimal.valueOf(USD_VND_RATE), 0, RoundingMode.HALF_UP);

                // (3.2.2) Tạo một yêu cầu giao dịch PayPal
                PaypalRequest paypalRequest = new PaypalRequest();

                paypalRequest.setIntent(OrderIntent.CAPTURE);
                paypalRequest.setPurchaseUnits(List.of(
                        new PaypalRequest.PurchaseUnit(
                                new PaypalRequest.PurchaseUnit.Money("USD", totalPayUSD.toString())
                        )
                ));

                paypalRequest.setApplicationContext(new PaypalRequest.PayPalAppContext()
                        .setBrandName("Electro")
                        .setLandingPage(PaymentLandingPage.BILLING)
                        .setReturnUrl(AppConstants.BACKEND_HOST + "/client-api/orders/success")
                        .setCancelUrl(AppConstants.BACKEND_HOST + "/client-api/orders/cancel"));

                PaypalResponse paypalResponse = payPalHttpClient.createPaypalTransaction(paypalRequest);

                // (3.2.3) Lưu order
                order.setPaypalOrderId(paypalResponse.getId());
                order.setPaypalOrderStatus(paypalResponse.getStatus().toString());

                orderRepository.save(order);

                // (3.2.4) Trả về đường dẫn checkout cho user
                for (PaypalResponse.Link link : paypalResponse.getLinks()) {
                    if ("approve".equals(link.getRel())) {
                        response.setOrderPaypalCheckoutLink(link.getHref());
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Cannot create PayPal transaction request!" + e);
            }
        } else {
            throw new RuntimeException("Cannot identify payment method");
        }

        // (4) Vô hiệu cart
        cart.setStatus(2); // Status 2: Vô hiệu lực
        cartRepository.save(cart);

        return response;
    }

    @Override
    public void captureTransactionPaypal(String paypalOrderId, String payerId) {
        Order order = orderRepository.findByPaypalOrderId(paypalOrderId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ORDER, FieldName.PAYPAL_ORDER_ID, paypalOrderId));

        order.setPaypalOrderStatus(OrderStatus.APPROVED.toString());

        try {
            // (1) Capture
            payPalHttpClient.capturePaypalTransaction(paypalOrderId, payerId);

            // (2) Cập nhật order
            order.setPaypalOrderStatus(OrderStatus.COMPLETED.toString());
            order.setPaymentStatus(2); // Status 2: Đã thanh toán

            // (3) Gửi notification
            Notification notification = new Notification()
                    .setUser(order.getUser())
                    .setType(NotificationType.CHECKOUT_PAYPAL_SUCCESS)
                    .setMessage(String.format("Đơn hàng %s của bạn đã được thanh toán thành công bằng PayPal.", order.getCode()))
                    .setAnchor("/order/detail/" + order.getCode())
                    .setStatus(1);

            notificationRepository.save(notification);

            notificationService.pushNotification(order.getUser().getUsername(),
                    notificationMapper.entityToResponse(notification));
        } catch (Exception e) {
            log.error("Cannot capture transaction: {0}", e);
        }

        orderRepository.save(order);
    }

    private Double calculateDiscountedPrice(Double price) {
        return price * (100) / 100;
    }

}
