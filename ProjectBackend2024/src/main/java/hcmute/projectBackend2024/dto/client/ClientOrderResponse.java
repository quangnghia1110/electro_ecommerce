package hcmute.projectBackend2024.dto.client;

import lombok.Data;
import org.springframework.lang.Nullable;

import hcmute.projectBackend2024.dto.authentication.UserResponse;
import hcmute.projectBackend2024.dto.order.OrderCancellationReasonResponse;
import hcmute.projectBackend2024.dto.order.OrderResourceResponse;
import hcmute.projectBackend2024.dto.order.OrderVariantResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
public class ClientOrderResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String code;
    private Integer status;
    private String toName;
    private String toPhone;
    private String toAddress;
    private String toWardName;
    private String toDistrictName;
    private String toProvinceName;
    private OrderResourceResponse orderResource;
    @Nullable
    private OrderCancellationReasonResponse orderCancellationReason;
    @Nullable
    private String note;
    private UserResponse user;
    private Set<OrderVariantResponse> orderVariants;
    private BigDecimal totalAmount;
    private BigDecimal tax;
    private BigDecimal shippingCost;
    private BigDecimal totalPay;
    private String paypalOrderId;
    private String paypalOrderStatus;
}
