package eu.epptec.carStop.service;

import eu.epptec.carStop.dto.ride.StopDto;
import eu.epptec.carStop.entity.PartRideEntity;
import eu.epptec.carStop.entity.PartRideReservationEntity;
import eu.epptec.carStop.entity.RideEntity;

import java.util.List;

public interface PartRideService {
    List<PartRideEntity> createPartRides(List<StopDto> stops, RideEntity rideEntity);
    void createReservation(PartRideReservationEntity partRideReservation);
}
