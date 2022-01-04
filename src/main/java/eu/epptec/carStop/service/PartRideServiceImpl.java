package eu.epptec.carStop.service;

import eu.epptec.carStop.dto.ride.StopDto;
import eu.epptec.carStop.entity.PartRideEntity;
import eu.epptec.carStop.entity.RideEntity;
import eu.epptec.carStop.service.interfaces.MapSpotService;
import eu.epptec.carStop.service.interfaces.PartRideService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartRideServiceImpl implements PartRideService {
    private final MapSpotService mapSpotService;

    public PartRideServiceImpl(MapSpotService mapSpotService) {
        this.mapSpotService = mapSpotService;
    }

    @Override
    public List<PartRideEntity> createPartRides(Iterable<StopDto> stops, RideEntity rideEntity) {
        List<PartRideEntity> result = new ArrayList<>();

        stops.forEach(stop -> {
            PartRideEntity partRide = new PartRideEntity();
            partRide.setDepartTime(stop.getDepartureTime());
            partRide.setStartSpot(mapSpotService.createOrFind(stop.getCity()));
        });

        return result;
    }
}
