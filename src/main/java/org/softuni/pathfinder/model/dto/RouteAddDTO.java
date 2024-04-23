package org.softuni.pathfinder.model.dto;

import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.model.enums.Level;

import java.util.Set;

public record RouteAddDTO(String name,
                          String description,
                          String gpxCoordinates,
                          Level level,
                          String videoUrl,
                          Set<CategoryNames> categories) {
}
