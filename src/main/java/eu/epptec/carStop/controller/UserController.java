package eu.epptec.carStop.controller;

import eu.epptec.carStop.dto.user.UserLoginDTO;
import eu.epptec.carStop.dto.user.UserPostDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/default")
public interface UserController<T> {
    @PostMapping
    T createNewUser(@Validated @RequestBody UserPostDTO model);

    @PostMapping("/login")
    T login(@Validated @RequestBody UserLoginDTO loginDTO);
}
