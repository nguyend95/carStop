package eu.epptec.carStop.service;

import eu.epptec.carStop.entity.MapSpotEntity;
import eu.epptec.carStop.entity.PartRideEntity;
import org.springframework.transaction.annotation.Transactional;

public interface MapSpotService {
    @Transactional
    MapSpotEntity createOrFind(String city);
}
