package org.softuni.pathfinder.web;

import org.softuni.pathfinder.Service.RouteService;
import org.softuni.pathfinder.model.dto.RouteAddDTO;
import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.model.enums.Level;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/routes")
public class RoutesController {

    private final RouteService routeService;

    public RoutesController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/add")
    public ModelAndView addRoute() {
        ModelAndView modelAndView = new ModelAndView("add-route");

        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categories", CategoryNames.values());

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addRoute(RouteAddDTO routeAddDTO) {

        routeService.addRoute(routeAddDTO);

        return new ModelAndView("redirect:/");
    }

    @GetMapping()
    public ModelAndView routes() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("routes", routeService.getAllRoutes());

        return modelAndView;
    }
}
