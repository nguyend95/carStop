package eu.epptec.carStop.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserLoginDTO extends UserBasicDTO{
    @NotBlank
    private String password;
}
