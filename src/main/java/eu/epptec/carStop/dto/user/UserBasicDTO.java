package eu.epptec.carStop.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class UserBasicDTO {
    @NotBlank
    @Size(max = 50)
    private String email;
}
