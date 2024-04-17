package org.softuni.pathfinder.Service;

import org.softuni.pathfinder.model.dto.UserLoginDTO;
import org.softuni.pathfinder.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    boolean loginUser(UserLoginDTO userLoginDTO);
}
