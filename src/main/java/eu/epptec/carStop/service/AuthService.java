package eu.epptec.carStop.service;

import eu.epptec.carStop.dto.jwt.JwtResponseDto;
import eu.epptec.carStop.dto.user.UserLoginDTO;

public interface AuthService {
    JwtResponseDto login(final UserLoginDTO login);
}
