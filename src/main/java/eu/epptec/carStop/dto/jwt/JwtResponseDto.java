package eu.epptec.carStop.dto.jwt;

import eu.epptec.carStop.dto.user.UserBasicDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class JwtResponseDto extends UserBasicDTO {
    private long id;
    private String username;
    private String type = "Bearer";
    private String accessToken;
    private List<String> roles;
    private String refreshToken;

    public JwtResponseDto(long id, String username, String accessToken, List<String> roles, String refreshToken) {
        this.id = id;
        this.username = username;
        this.accessToken = accessToken;
        this.roles = roles;
        this.refreshToken = refreshToken;
    }
}
