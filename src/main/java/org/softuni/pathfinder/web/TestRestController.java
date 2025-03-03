package org.softuni.pathfinder.web;

import org.softuni.pathfinder.Service.UserService;
import org.softuni.pathfinder.model.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class TestRestController {
    private final UserService userService;

    public TestRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
//    @GetMapping("/all")
    List<User> getAll() {
        return this.userService.getAll();
    }
}
