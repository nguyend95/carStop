package eu.epptec.carStop.service;

import eu.epptec.carStop.dto.reservation.ReservationGetDto;

public interface ReservationService {
    ReservationGetDto getReservation(long id);
}
