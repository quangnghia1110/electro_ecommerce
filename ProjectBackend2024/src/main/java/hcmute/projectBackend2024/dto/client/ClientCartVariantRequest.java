package hcmute.projectBackend2024.dto.client;

import lombok.Data;

@Data
public class ClientCartVariantRequest {
    private Long variantId;
    private Integer quantity;
}
