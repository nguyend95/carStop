package eu.epptec.carStop.dto.jwt;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TokenRefreshResponseDto {
    private String accessToken;
    private String type = "Bearer";
    private String refreshToken;

    public TokenRefreshResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
