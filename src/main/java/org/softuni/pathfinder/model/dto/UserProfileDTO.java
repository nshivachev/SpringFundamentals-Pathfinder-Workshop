package org.softuni.pathfinder.model.dto;

import org.softuni.pathfinder.model.enums.Level;

public record UserProfileDTO(String fullName, String username, int age, Level level) {
}
