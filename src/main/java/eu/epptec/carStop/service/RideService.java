package eu.epptec.carStop.service;

import eu.epptec.carStop.dto.reservation.ReservationDto;
import eu.epptec.carStop.dto.reservation.ReservationGetDto;
import eu.epptec.carStop.dto.ride.FindRideDto;
import eu.epptec.carStop.dto.ride.GetRideDto;
import eu.epptec.carStop.dto.ride.PostRideDto;
import eu.epptec.carStop.entity.ReservationEntity;

import java.util.List;

public interface RideService {
    GetRideDto createRide(PostRideDto ride);
    GetRideDto getRide(String rideId);

    List<GetRideDto> getAllRides();

    List<GetRideDto> findRideByInformation(FindRideDto findInformation);

    ReservationGetDto createReservation(long id, ReservationDto reservationInfo);

    boolean rideExists(long id);

}
