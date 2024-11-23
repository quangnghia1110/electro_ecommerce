package hcmute.projectBackend2024.dto.client;

import lombok.Data;

@Data
public class ClientCartVariantKeyRequest {
    private Long cartId;
    private Long variantId;
}
