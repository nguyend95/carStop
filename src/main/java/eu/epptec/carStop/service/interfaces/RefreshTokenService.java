package eu.epptec.carStop.service.interfaces;

import eu.epptec.carStop.entity.RefreshTokenEntity;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshTokenEntity> findByToken(String refreshToken);
    RefreshTokenEntity verifyExpiration(RefreshTokenEntity token);
    RefreshTokenEntity createRefreshToken(long userId);
}
