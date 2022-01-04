package eu.epptec.carStop.repository;

import eu.epptec.carStop.entity.RoleEntity;
import eu.epptec.carStop.enums.ROLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(ROLE name);
}
