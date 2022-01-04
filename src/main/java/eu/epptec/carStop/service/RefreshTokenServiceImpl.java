package eu.epptec.carStop.service;

import eu.epptec.carStop.entity.RefreshTokenEntity;
import eu.epptec.carStop.error.TokenRefreshException;
import eu.epptec.carStop.repository.RefreshTokenRepository;
import eu.epptec.carStop.repository.UserRepository;
import eu.epptec.carStop.service.interfaces.RefreshTokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Value("${jwtRefreshExpirationMs}")
    private Long refreshTokenDuration;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<RefreshTokenEntity> findByToken(String refreshToken) {
        Optional<RefreshTokenEntity> token = this.refreshTokenRepository.findRefreshTokenEntityByToken(refreshToken);
        RefreshTokenEntity copyToReturn = new RefreshTokenEntity();

        if (token.isPresent()){
            BeanUtils.copyProperties(copyToReturn, token);
            return Optional.of(copyToReturn);
        }

        return Optional.empty();
    }

    @Override
    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpiryDate().compareTo(new Date()) < 0){
            refreshTokenRepository.delete(this.refreshTokenRepository.findRefreshTokenEntityByToken(token.getToken()).get());
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Override
    public RefreshTokenEntity createRefreshToken(long userId) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();

        refreshToken.setUser(this.userRepository.findById(userId).get());
        refreshToken.setExpiryDate(new Date((new Date()).getTime() + refreshTokenDuration));
        refreshToken.setToken(UUID.randomUUID().toString());

        this.refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }


}
