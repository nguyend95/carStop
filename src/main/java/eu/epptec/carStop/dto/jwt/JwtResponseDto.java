package eu.epptec.carStop.dto.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtResponseDto{
    private String type = "Bearer";
    private String accessToken;
    public JwtResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
