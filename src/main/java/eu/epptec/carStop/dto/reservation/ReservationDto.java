package eu.epptec.carStop.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ReservationDto {
    private int seats;
    private List<Integer> partRideId;
}
