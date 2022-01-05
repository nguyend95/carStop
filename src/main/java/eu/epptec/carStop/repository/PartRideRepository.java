package eu.epptec.carStop.repository;

import eu.epptec.carStop.entity.PartRideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRideRepository extends JpaRepository<PartRideEntity, Long> {
}
