package org.softuni.pathfinder.web;

import jakarta.validation.Valid;
import org.softuni.pathfinder.Service.RouteService;
import org.softuni.pathfinder.model.dto.RouteAddDTO;
import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.model.enums.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/routes")
public class RoutesController {

    @Value("${binding-result-package}")
    private String bindingResultPath;

    private final static String DOT = ".";

    private final RouteService routeService;

    public RoutesController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/add")
    public ModelAndView addRoute(Model model) {

        if (!model.containsAttribute("routeAdd")) {
            model.addAttribute("routeAdd", new RouteAddDTO());
        }

        ModelAndView modelAndView = new ModelAndView("add-route");

        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categories", CategoryNames.values());

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addRoute(
            @Valid RouteAddDTO routeAddDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            final String attributeName = "routeAdd";

            redirectAttributes
                    .addFlashAttribute(attributeName, routeAddDTO)
                    .addFlashAttribute(bindingResultPath + DOT + attributeName, bindingResult);
            modelAndView.setViewName("redirect:add");
        } else {
            routeService.addRoute(routeAddDTO);
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping()
    public ModelAndView routes() {

        ModelAndView modelAndView = new ModelAndView("routes");
        modelAndView.addObject("routes", routeService.getAllRoutes());

        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView routeDetails(@PathVariable("id") Long id) {

        ModelAndView modelAndView = new ModelAndView("route-details");
        modelAndView.addObject("route", routeService.getRouteDetails(id));

        return modelAndView;
    }
}
