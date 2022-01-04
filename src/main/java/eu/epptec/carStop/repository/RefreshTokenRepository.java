package eu.epptec.carStop.repository;

import eu.epptec.carStop.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findRefreshTokenEntityByToken(String token);
    Optional<RefreshTokenEntity> findRefreshTokenEntityById(Long id);
}
