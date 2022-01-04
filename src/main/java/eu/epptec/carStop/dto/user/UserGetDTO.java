package eu.epptec.carStop.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserGetDTO extends UserBasicDTO{
    private long id;
    private String firstName;
    private String lastName;
    private int rating;
}
