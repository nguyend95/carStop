package eu.epptec.carStop.service.interfaces;

import eu.epptec.carStop.entity.MapSpotEntity;

public interface MapSpotService {
    MapSpotEntity createOrFind(String city);
}
