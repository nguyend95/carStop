package eu.epptec.carStop.dto.reservation;

import eu.epptec.carStop.dto.partRide.PartRideGetDto;
import eu.epptec.carStop.dto.user.UserGetDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ReservationGetDto {
    private long id;
    private int seats;
    private UserGetDTO driver;
    private List<PartRideGetDto> partRides;
}
