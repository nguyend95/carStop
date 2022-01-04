package eu.epptec.carStop.service;

import eu.epptec.carStop.dto.ride.GetRideDto;
import eu.epptec.carStop.dto.ride.PostRideDto;
import eu.epptec.carStop.entity.RideEntity;
import eu.epptec.carStop.service.interfaces.PartRideService;
import eu.epptec.carStop.service.interfaces.RideService;
import eu.epptec.carStop.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImpl implements RideService {
    private final PartRideService partRideService;
    private final UserService userService;

    public RideServiceImpl(PartRideService partRideService, UserService userService) {
        this.partRideService = partRideService;
        this.userService = userService;
    }

    @Override
    public GetRideDto createRide(PostRideDto ride) {
        RideEntity rideEntity = this.newRideFromInput(ride);

        return null;
    }

    private RideEntity newRideFromInput(PostRideDto ride) {
        RideEntity rideEntity = new RideEntity();

        rideEntity.setDriverId(userService.get(ride.getDriverId()).get());
        rideEntity.setMessage(ride.getNotes());
        rideEntity.setTotalSeats(ride.getTotalSeats());
        rideEntity.setMusic(ride.isMusic());
        rideEntity.setPets(ride.isPets());
        rideEntity.setSmoke(ride.isSmoke());
        rideEntity.setPartRides(this.partRideService.createPartRides(ride.getStops(), rideEntity));

        return rideEntity;
    }

    @Override
    public GetRideDto getRide(String rideId) {
        return null;
    }


}
