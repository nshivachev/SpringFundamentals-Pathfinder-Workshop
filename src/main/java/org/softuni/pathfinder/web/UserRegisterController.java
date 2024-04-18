package org.softuni.pathfinder.web;

import org.softuni.pathfinder.Service.UserService;
import org.softuni.pathfinder.model.dto.UserRegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UserRegisterController {
    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(UserRegisterDTO userRegisterDTO) {
        return userService.registerUser(userRegisterDTO) ? "redirect:login" : "register";
    }
}
