package org.softuni.pathfinder.repository;

import org.softuni.pathfinder.model.entity.Role;
import org.softuni.pathfinder.model.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRoles name);
}
