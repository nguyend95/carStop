package eu.epptec.carStop.dto.ride;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
public class RideDto {
    @NotNull @NotEmpty
    private List<StopDto> stops;
    private String notes;
    private boolean music;
    private boolean smoke;
    private boolean pets;
    private int totalSeats;
}
