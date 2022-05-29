package com.panilya.authappserver.repository;

import com.panilya.authappserver.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserRole, Long> {

    UserRole getById(Long id);
}
