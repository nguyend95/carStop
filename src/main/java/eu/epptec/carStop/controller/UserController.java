package eu.epptec.carStop.controller;

import eu.epptec.carStop.dto.user.UserPostDTO;
import eu.epptec.carStop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserPostDTO> createNewUser(@Validated @RequestBody UserPostDTO model){
        if (!this.userService.checkInput(model)){
            return ResponseEntity.status(400).body(null);
        }

        return ResponseEntity.ok(this.userService.createUser(model));
    }
}
