package eu.epptec.carStop.dto.jwt;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class TokenRefreshRequestDto {
    @NotBlank
    private String refreshToken;
}
