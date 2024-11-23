package hcmute.projectBackend2024.dto.client;

import lombok.Data;
import org.springframework.lang.Nullable;

import hcmute.projectBackend2024.entity.cashbook.PaymentMethodType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
public class ClientOrderDetailResponse {
    private Long orderId;
    private Instant orderCreatedAt;
    private String orderCode;
    private Integer orderStatus;
    private String orderToName;
    private String orderToPhone;
    private String orderToAddress;
    private String orderToWardName;
    private String orderToDistrictName;
    private String orderToProvinceName;
    private BigDecimal orderTotalAmount;
    private BigDecimal orderTax;
    private BigDecimal orderShippingCost;
    private BigDecimal orderTotalPay;
    private PaymentMethodType orderPaymentMethodType;
    private Integer orderPaymentStatus;
    private Set<ClientOrderVariantResponse> orderItems;
   
}
