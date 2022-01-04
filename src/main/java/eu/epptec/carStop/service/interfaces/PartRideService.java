package eu.epptec.carStop.service.interfaces;

import eu.epptec.carStop.dto.ride.StopDto;
import eu.epptec.carStop.entity.PartRideEntity;
import eu.epptec.carStop.entity.RideEntity;

import java.util.List;

public interface PartRideService {
    List<PartRideEntity> createPartRides(Iterable<StopDto> stops, RideEntity rideEntity);
}
