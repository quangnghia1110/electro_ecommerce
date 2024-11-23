package hcmute.projectBackend2024.dto.client;

import hcmute.projectBackend2024.entity.cashbook.PaymentMethodType;
import lombok.Data;

@Data
public class ClientSimpleOrderRequest {
    private PaymentMethodType paymentMethodType;
}
