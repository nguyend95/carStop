package eu.epptec.carStop.controller;

import eu.epptec.carStop.dto.user.UserPostDTO;
import eu.epptec.carStop.entity.UserEntity;
import eu.epptec.carStop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String viewHomePage() {
        return "homepage";
    }

    @GetMapping("/user")
    public String getAllUser(Model model){
        List<UserEntity> users = (List<UserEntity>) userService.getAll();
        model.addAttribute("users", users);
        return "user";
    }

    @PostMapping("/user")
    public String createNewUser(@Validated @ModelAttribute("user") UserPostDTO user,
                                BindingResult result){
        if (!this.userService.checkInput(user)){
            result.rejectValue("email", null, "There is already and user registered with that email.");
        }

        if (result.hasErrors()){
            return "registration_form";
        }

        this.userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model){
        UserPostDTO userDto = new UserPostDTO();

        model.addAttribute("user", userDto);
        return "registration_form";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "error";
    }
}
