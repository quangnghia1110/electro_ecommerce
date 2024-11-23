package hcmute.projectBackend2024.dto.cashbook;

import lombok.Data;

import java.time.Instant;

import hcmute.projectBackend2024.entity.cashbook.PaymentMethodType;

@Data
public class PaymentMethodResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    private PaymentMethodType code;
    private Integer status;
}
