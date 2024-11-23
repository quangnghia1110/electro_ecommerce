package hcmute.projectBackend2024.service.authetication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import hcmute.projectBackend2024.constant.FieldName;
import hcmute.projectBackend2024.constant.ResourceName;
import hcmute.projectBackend2024.entity.authentication.RefreshToken;
import hcmute.projectBackend2024.exception.RefreshTokenException;
import hcmute.projectBackend2024.exception.ResourceNotFoundException;
import hcmute.projectBackend2024.repository.authentication.RefreshTokenRepository;
import hcmute.projectBackend2024.repository.authentication.UserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefresherTokenServiceImpl implements RefreshTokenService {

    @Value("${electro.app.jwtRefreshExpirationMs}")
    private int jwtRefreshExpirationMs;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(Authentication authentication) {
        String username = authentication.getName();
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.USER, FieldName.USERNAME, username)));
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshExpirationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException("Refresh token was expired. Please make a new signin request!");
        }

        return refreshToken;
    }
}
