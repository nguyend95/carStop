package eu.epptec.carStop.controller;

import eu.epptec.carStop.dto.user.UserPostDTO;
import eu.epptec.carStop.security.AuthenticationFacade;
import eu.epptec.carStop.utils.JwtUtils;
import eu.epptec.carStop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserControllerRest {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationFacade authenticationFacade;

    public UserControllerRest(UserService userService, JwtUtils jwtUtils,
                              AuthenticationFacade authenticationFacade) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationFacade = authenticationFacade;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserPostDTO model) {
        if (!this.userService.checkInput(model)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already used.");
        }

        return ResponseEntity.ok(this.userService.save(model));
    }
}
