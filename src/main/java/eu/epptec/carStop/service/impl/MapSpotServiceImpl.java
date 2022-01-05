package eu.epptec.carStop.service.impl;

import eu.epptec.carStop.entity.MapSpotEntity;
import eu.epptec.carStop.repository.MapSpotRepository;
import eu.epptec.carStop.service.MapSpotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MapSpotServiceImpl implements MapSpotService {
    private final MapSpotRepository mapSpotRepository;

    public MapSpotServiceImpl(MapSpotRepository mapSpotRepository) {
        this.mapSpotRepository = mapSpotRepository;
    }

    @Override
    @Transactional
    public MapSpotEntity createOrFind(String city) {
        Optional<MapSpotEntity> mapSpot;

        if ((mapSpot = mapSpotRepository.findByCityName(city.toLowerCase())).isEmpty()){
            mapSpot = this.createNewMapSpot(city);
        }

        return mapSpot.get();
    }


    private Optional<MapSpotEntity> createNewMapSpot(String city) {
        MapSpotEntity mapSpot = new MapSpotEntity();

        mapSpot.setCityName(city.toLowerCase());
        this.mapSpotRepository.save(mapSpot);

        return Optional.of(mapSpot);
    }
}
