package hcmute.projectBackend2024.dto.authentication;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
