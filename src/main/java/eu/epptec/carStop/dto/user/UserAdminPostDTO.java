package eu.epptec.carStop.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserAdminPostDTO {
    List<String> roles;
}
