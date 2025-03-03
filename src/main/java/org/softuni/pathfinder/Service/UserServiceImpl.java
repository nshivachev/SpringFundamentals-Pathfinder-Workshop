package org.softuni.pathfinder.Service;

import org.softuni.pathfinder.exceptions.UserNotFoundException;
import org.softuni.pathfinder.model.dto.UserLoginDTO;
import org.softuni.pathfinder.model.dto.UserProfileDTO;
import org.softuni.pathfinder.model.dto.UserRegisterDTO;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.model.enums.Level;
import org.softuni.pathfinder.model.enums.UserRoles;
import org.softuni.pathfinder.repository.RoleRepository;
import org.softuni.pathfinder.repository.UserRepository;
import org.softuni.pathfinder.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
        this.roleRepository = roleRepository;
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
                .setUsername(user.get().getUsername())
                .setFullName(user.get().getFullName());

        return isLoginSuccessful;
    }

    @Override
    public boolean registerUser(UserRegisterDTO userRegisterDTO) {
        boolean isRegisterSuccessful = false;

        Optional<User> user = userRepository.findByUsername(userRegisterDTO.username());

        if (user.isPresent() || !userRegisterDTO.password().equals(userRegisterDTO.confirmPassword())) {
            return isRegisterSuccessful;
        }

        userRepository.save(map(userRegisterDTO));
        isRegisterSuccessful = true;

        return isRegisterSuccessful;
    }

    @Override
    public void logoutUser() {
        currentUser.logout();
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserProfileDTO getUserProfile() {
        User user = userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new UserNotFoundException(String.format("User %s not found", currentUser.getUsername())));

        return map(user);
    }

    private User map(UserRegisterDTO userRegisterDTO) {
        User user = new User();

        user.setUsername(userRegisterDTO.username());
        user.setFullName(userRegisterDTO.fullName());
        user.setEmail(userRegisterDTO.email());
        user.setAge(userRegisterDTO.age());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.password()));
        user.setLevel(Level.BEGINNER);
        user.setRoles(Set.of(roleRepository.findByName(UserRoles.USER).orElseThrow(IllegalArgumentException::new)));

        return user;
    }

    private UserProfileDTO map(User user) {
        return new UserProfileDTO(user.getFullName(), user.getUsername(), user.getAge(), user.getLevel());
    }
}
