package hcmute.projectBackend2024.dto.order;

import lombok.Data;

@Data
public class OrderVariantKeyRequest {
    private Long orderId;
    private Long variantId;
}