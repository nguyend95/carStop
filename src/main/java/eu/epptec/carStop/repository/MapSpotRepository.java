package eu.epptec.carStop.repository;

import eu.epptec.carStop.entity.MapSpotEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MapSpotRepository extends CrudRepository<MapSpotEntity, Long> {
    Optional<MapSpotEntity> findByCityName(String city);
}
