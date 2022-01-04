package eu.epptec.carStop.controller;

import eu.epptec.carStop.dto.jwt.JwtResponseDto;
import eu.epptec.carStop.dto.jwt.TokenRefreshRequestDto;
import eu.epptec.carStop.dto.jwt.TokenRefreshResponseDto;
import eu.epptec.carStop.dto.user.UserLoginDTO;
import eu.epptec.carStop.dto.user.UserPostDTO;
import eu.epptec.carStop.entity.RefreshTokenEntity;
import eu.epptec.carStop.error.TokenRefreshException;
import eu.epptec.carStop.security.AuthenticationFacade;
import eu.epptec.carStop.security.jwt.JwtUtils;
import eu.epptec.carStop.security.service.UserDetailsImpl;
import eu.epptec.carStop.service.interfaces.RefreshTokenService;
import eu.epptec.carStop.service.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserControllerRestImpl implements UserController<ResponseEntity<?>>{
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationFacade authenticationFacade;
    private final RefreshTokenService refreshTokenService;

    public UserControllerRestImpl(UserService userService, JwtUtils jwtUtils,
                                  AuthenticationFacade authenticationFacade, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationFacade = authenticationFacade;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserPostDTO model) {
        if (!this.userService.checkInput(model)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already used.");
        }

        return ResponseEntity.ok(this.userService.save(model));
    }

    @Override
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO loginDTO) {
        Authentication authentication = this.authenticationFacade.getAuthentication();
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponseDto(userDetails.getId(),
                userDetails.getEmail(), jwtToken, roles,
                refreshTokenService.createRefreshToken(userDetails.getId()).getToken()));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequestDto request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshTokenEntity::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getEmail());
                    return ResponseEntity.ok(new TokenRefreshResponseDto(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
