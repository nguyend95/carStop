package eu.epptec.carStop.controller;

import eu.epptec.carStop.dto.jwt.JwtResponseDto;
import eu.epptec.carStop.dto.user.UserLoginDTO;
import eu.epptec.carStop.service.AuthService;
import eu.epptec.carStop.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public JwtResponseDto login(@Valid @RequestBody UserLoginDTO loginDTO) {
        return this.authService.login(loginDTO);
    }
}
