package com.example.fox3project.service;

import com.example.fox3project.entity.Role;
import com.example.fox3project.entity.User;
import com.example.fox3project.entity.dto.add.request.UserCreateRequest;
import com.example.fox3project.entity.dto.add.response.UserCreateResponse;
import com.example.fox3project.exception.IsExistException;
import com.example.fox3project.mapper.UserMapper;
import com.example.fox3project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Transactional
    public UserCreateResponse add(UserCreateRequest userCreateRequest) {
        User user = userMapper.mapCreateUserRequestToUser(userCreateRequest);

        if (validateUser(user)) {
            Set<Role> roles = roleService.createRoles(userCreateRequest);
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
            user = userRepository.save(user);
            return userMapper.mapUserToUserCreateResponse(user);
        }
        return null;
    }

    private boolean validateUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IsExistException("User with name " + user.getUsername() + " is exist!", "add");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IsExistException("User with email " + user.getEmail() + " is exist!", "add");
        }

        return true;
    }
}
