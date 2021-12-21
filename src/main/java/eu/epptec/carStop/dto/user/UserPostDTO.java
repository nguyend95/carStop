package eu.epptec.carStop.dto.user;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter @Setter
public class UserPostDTO extends UserBasicDTO{
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;
}
