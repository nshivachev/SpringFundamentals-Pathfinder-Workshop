package org.softuni.pathfinder.Service;

import org.softuni.pathfinder.model.dto.UserLoginDTO;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.repository.UserRepository;
import org.softuni.pathfinder.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean loginUser(UserLoginDTO userLoginDTO) {
        boolean isLoginSuccessful = false;

        Optional<User> user = userRepository.findByUsername(userLoginDTO.username());

        if (user.isEmpty() || !passwordEncoder.matches(userLoginDTO.password(), user.get().getPassword())) {
            currentUser.logout();
            return isLoginSuccessful;
        }

        isLoginSuccessful = true;

        currentUser.setLogged(true)
                .setFullName(user.get().getFullName());

        return isLoginSuccessful;
    }
}
