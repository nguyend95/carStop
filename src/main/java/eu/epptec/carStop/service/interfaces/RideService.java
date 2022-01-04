package eu.epptec.carStop.service.interfaces;

import eu.epptec.carStop.dto.ride.GetRideDto;
import eu.epptec.carStop.dto.ride.PostRideDto;

public interface RideService {
    GetRideDto createRide(PostRideDto ride);
    GetRideDto getRide(String rideId);
}
