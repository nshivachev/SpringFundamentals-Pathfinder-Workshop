package org.softuni.pathfinder.Service;

import org.softuni.pathfinder.model.dto.RouteAddDTO;
import org.softuni.pathfinder.model.dto.RouteDetailsDTO;
import org.softuni.pathfinder.model.dto.RouteGetAllDTO;

import java.util.List;

public interface RouteService {
    void addRoute(RouteAddDTO routeAddDTO);

    List<RouteGetAllDTO> getAllRoutes();

    RouteDetailsDTO getRouteDetails(Long id);
}
