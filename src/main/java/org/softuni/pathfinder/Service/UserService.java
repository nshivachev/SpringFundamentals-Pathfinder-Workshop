package org.softuni.pathfinder.Service;

import org.softuni.pathfinder.model.dto.UserLoginDTO;
import org.softuni.pathfinder.model.dto.UserProfileDTO;
import org.softuni.pathfinder.model.dto.UserRegisterDTO;
import org.softuni.pathfinder.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    boolean loginUser(UserLoginDTO userLoginDTO);

    boolean registerUser(UserRegisterDTO userRegisterDTO);

    void logoutUser();

    Optional<User> getByUsername(String username);

    UserProfileDTO getUserProfile();


}
