package eu.epptec.carStop.dto.partRide;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class PartRideGetDto {
    private long id;
    private Date departTime;
    private String city;
}
