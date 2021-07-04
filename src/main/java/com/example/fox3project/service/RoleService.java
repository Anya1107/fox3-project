package com.example.fox3project.service;

import com.example.fox3project.entity.ERole;
import com.example.fox3project.entity.Role;
import com.example.fox3project.entity.dto.add.request.UserCreateRequest;
import com.example.fox3project.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Set<Role> createRoles(UserCreateRequest userCreateRequest) {
        Set<String> reqRoles = userCreateRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if(reqRoles == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role User is not found!"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                if ("admin".equals(r)) {
                    Role adminRole = roleRepository
                            .findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Role Admin is not found"));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository
                            .findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Role User is not found"));
                    roles.add(userRole);
                }
            });
        }
        return roles;
    }
}
