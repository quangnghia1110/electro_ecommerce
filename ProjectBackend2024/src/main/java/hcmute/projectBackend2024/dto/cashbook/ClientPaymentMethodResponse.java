package hcmute.projectBackend2024.dto.cashbook;

import hcmute.projectBackend2024.entity.cashbook.PaymentMethodType;
import lombok.Data;

@Data
public class ClientPaymentMethodResponse {
    private Long paymentMethodId;
    private String paymentMethodName;
    private PaymentMethodType paymentMethodCode;
}
