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
        route.setName(routeAddDTO.name());
        route.setDescription(routeAddDTO.description());
        route.setGpxCoordinates(routeAddDTO.gpxCoordinates());
        route.setVideoUrl(routeAddDTO.videoUrl());
        route.setLevel(routeAddDTO.level());
        route.setAuthor(userService.getByUsername(currentUser.getUsername()).orElseThrow(IllegalArgumentException::new));
        if (null != routeAddDTO.categories()) {
            route.addCategories(categoryRepository.findByNameIn(routeAddDTO.categories()));
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
                route.getLevel(),
                route.getDescription(),
                route.getVideoUrl(),
                route.getAuthor().getUsername()
        );
    }
}
