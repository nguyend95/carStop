package eu.epptec.carStop.dto.ride;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Getter @Setter
public class StopDto {
    @NotBlank
    private String city;

    @NotNull
    private Date departureTime;

    private int price;
}
