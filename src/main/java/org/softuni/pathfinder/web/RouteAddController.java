package org.softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteAddController {
    @GetMapping("/routes/add")
    public String addRoute() {
        return "add-route";
    }
}
