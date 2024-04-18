package org.softuni.pathfinder.model.dto;

public record UserRegisterDTO(String username,
                              String fullName,
                              String email,
                              int age,
                              String password,
                              String confirmPassword) {
}
