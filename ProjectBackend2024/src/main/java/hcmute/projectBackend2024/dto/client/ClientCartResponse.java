package hcmute.projectBackend2024.dto.client;

import lombok.Data;

import java.util.Set;

@Data
public class ClientCartResponse {
    private Long cartId;
    private Set<ClientCartVariantResponse> cartItems;
}
