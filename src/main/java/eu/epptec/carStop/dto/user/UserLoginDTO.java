package eu.epptec.carStop.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserLoginDTO extends UserBasicDTO{
    private String password;
}
