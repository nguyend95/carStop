package eu.epptec.carStop.service;

import eu.epptec.carStop.entity.MapSpotEntity;
import eu.epptec.carStop.repository.MapSpotRepository;
import eu.epptec.carStop.service.interfaces.MapSpotService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MapSpotServiceImpl implements MapSpotService {
    private final MapSpotRepository mapSpotRepository;

    public MapSpotServiceImpl(MapSpotRepository mapSpotRepository) {
        this.mapSpotRepository = mapSpotRepository;
    }

    @Override
    public MapSpotEntity createOrFind(String city) {
        Optional<MapSpotEntity> mapSpot;

        if ((mapSpot = mapSpotRepository.findByCityName(city)).isEmpty()){
            mapSpot = this.createNewMapSpot(city);
        }

        return mapSpot.get();
    }

    private Optional<MapSpotEntity> createNewMapSpot(String city) {
        MapSpotEntity mapSpot = new MapSpotEntity();

        mapSpot.setCityName(city);

        return Optional.of(mapSpot);
    }
}
