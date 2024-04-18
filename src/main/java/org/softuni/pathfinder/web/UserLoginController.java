package org.softuni.pathfinder.web;

import org.softuni.pathfinder.Service.UserService;
import org.softuni.pathfinder.model.dto.UserLoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("users")
@Controller
public class UserLoginController {
    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO) {
        return userService.loginUser(userLoginDTO) ? "redirect:/" : "login";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logoutUser();

        return "redirect:/";
    }
}
