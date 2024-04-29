package org.softuni.pathfinder.web;

import org.softuni.pathfinder.Service.UserService;
import org.softuni.pathfinder.model.dto.UserProfileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/users")
@Controller
public class UserProfileController {
    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        UserProfileDTO userProfileViewModel = this.userService.getUserProfile();

        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("userProfileViewModel", userProfileViewModel);

        return modelAndView;
    }
}
