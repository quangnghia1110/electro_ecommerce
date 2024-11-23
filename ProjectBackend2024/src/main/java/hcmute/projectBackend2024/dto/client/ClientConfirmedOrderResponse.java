package hcmute.projectBackend2024.dto.client;

import lombok.Data;
import org.springframework.lang.Nullable;

import hcmute.projectBackend2024.entity.cashbook.PaymentMethodType;

@Data
public class ClientConfirmedOrderResponse {
    private String orderCode;
    private PaymentMethodType orderPaymentMethodType;
    @Nullable
    private String orderPaypalCheckoutLink;
}
