package eu.epptec.carStop.dto.ride;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRideDto extends RideDto{
    private int driverId;
}
