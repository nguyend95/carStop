package eu.epptec.carStop.dto.ride;

import eu.epptec.carStop.dto.user.UserGetDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetRideDto extends RideDto{
    private int id;
    private UserGetDTO driver;
}
