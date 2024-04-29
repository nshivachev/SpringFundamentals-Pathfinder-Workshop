package org.softuni.pathfinder.model.dto;

import org.softuni.pathfinder.model.enums.Level;

public record RouteDetailsDTO(Long id, String name, Level level, String description, String videoUrl, String authorName) {
}
