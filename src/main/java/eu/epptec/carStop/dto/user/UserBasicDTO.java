package eu.epptec.carStop.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserBasicDTO {
    private long id;

    @NotBlank
    private String email;
}
