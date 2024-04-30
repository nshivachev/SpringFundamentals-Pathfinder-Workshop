package org.softuni.pathfinder.Service;

import org.softuni.pathfinder.exceptions.RouteNotFoundException;
import org.softuni.pathfinder.model.dto.RouteAddDTO;
import org.softuni.pathfinder.model.dto.RouteDetailsDTO;
import org.softuni.pathfinder.model.dto.RouteGetAllDTO;
import org.softuni.pathfinder.model.entity.Route;
import org.softuni.pathfinder.repository.CategoryRepository;
import org.softuni.pathfinder.repository.RouteRepository;
import org.softuni.pathfinder.util.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final CurrentUser currentUser;

    public RouteServiceImpl(RouteRepository routeRepository, CategoryRepository categoryRepository, UserService userService, CurrentUser currentUser) {
        this.routeRepository = routeRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @Override
    public void addRoute(RouteAddDTO routeAddDTO) {
        routeRepository.save(map(routeAddDTO));
    }

    @Override
    public List<RouteGetAllDTO> getAllRoutes() {
        return routeRepository.findAll().stream().map(this::maToRouteGetAllDTO).toList();
    }

    @Override
    public RouteDetailsDTO getRouteDetails(Long id) {
        return mapToRouteDetailsDTO(routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException(String.format("Route with id %d is not found", id))));
    }

    private Route map(RouteAddDTO routeAddDTO) {
        Route route = new Route();

        route.setName(routeAddDTO.getName());
        route.setDescription(routeAddDTO.getDescription());
//        route.setGpxCoordinates(routeAddDTO.getGpxCoordinates());
        route.setAuthor(userService.getByUsername(currentUser.getUsername()).orElseThrow(IllegalArgumentException::new));
        route.setLevel(routeAddDTO.getLevel());

        Pattern compile = Pattern.compile("v=(.*)");
        Matcher matcher = compile.matcher(routeAddDTO.getVideoUrl());

        if (matcher.find()) {
            route.setVideoUrl(matcher.group(1));
        }

        if (null != routeAddDTO.getCategories()) {
            route.addCategories(categoryRepository.findByNameIn(routeAddDTO.getCategories()));
        }

        return route;
    }

    private RouteGetAllDTO maToRouteGetAllDTO(Route route) {
        return new RouteGetAllDTO(route.getId(), route.getImageUrl(), route.getName(), route.getDescription());
    }

    private RouteDetailsDTO mapToRouteDetailsDTO(Route route) {
        return new RouteDetailsDTO(
                route.getId(),
                route.getName(),
                route.getLevel().toString(),
                route.getDescription(),
                route.getVideoUrl(),
                route.getAuthor().getUsername()
        );
    }
}
