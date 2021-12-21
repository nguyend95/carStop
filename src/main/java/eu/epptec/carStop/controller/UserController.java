package eu.epptec.carStop.controller;

import eu.epptec.carStop.dto.user.UserPostDTO;
import eu.epptec.carStop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String createNewUser(@Validated @RequestBody UserPostDTO model,
                                BindingResult result){
        if (!this.userService.checkInput(model)){
            result.rejectValue("email", null, "There is already and user registered with that email.");
        }

        if (result.hasErrors()){
            return "registration";
        }

        this.userService.save(model);
        return "redirect:/registration?success";
    }

    @GetMapping("/registration_form")
    public String showRegistrationForm(Model model){
        return "registration_form";
    }

    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }
}
