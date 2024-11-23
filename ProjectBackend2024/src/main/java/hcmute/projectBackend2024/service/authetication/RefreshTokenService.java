package hcmute.projectBackend2024.service.authetication;

import org.springframework.security.core.Authentication;

import hcmute.projectBackend2024.entity.authentication.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(Authentication authentication);

    RefreshToken verifyExpiration(RefreshToken refreshToken);

}
