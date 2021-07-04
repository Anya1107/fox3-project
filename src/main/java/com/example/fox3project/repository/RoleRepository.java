package com.example.fox3project.repository;

import com.example.fox3project.entity.ERole;
import com.example.fox3project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
