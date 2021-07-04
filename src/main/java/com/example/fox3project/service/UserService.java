package com.example.fox3project.service;

import com.example.fox3project.entity.Role;
import com.example.fox3project.entity.User;
import com.example.fox3project.entity.dto.add.request.UserCreateRequest;
import com.example.fox3project.entity.dto.add.response.UserCreateResponse;
import com.example.fox3project.entity.dto.get.response.UserGetResponse;
import com.example.fox3project.entity.dto.update.request.UserUpdateRequest;
import com.example.fox3project.entity.dto.update.response.UserUpdateResponse;
import com.example.fox3project.exception.IsExistException;
import com.example.fox3project.exception.NotFoundException;
import com.example.fox3project.mapper.UserMapper;
import com.example.fox3project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;

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

    @Transactional
    public void delete(long id) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        userRepository.delete(user);
    }

    public UserGetResponse findById(long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id: " + id + " not found", "findById"));
        return userMapper.mapUserToUserGetResponse(user);
    }

    public UserGetResponse findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username: " + username + " not found"));
        return userMapper.mapUserToUserGetResponse(user);
    }

    public List<UserGetResponse> findAll(Pageable pageable) {
        List<User> users = entityManager
                .createQuery("select distinct u from User u order by u.id")
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return userMapper.mapUserListToGetUserResponseList(users);
    }

    public UserUpdateResponse update(long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        updateUserFromRequestDto(userUpdateRequest, user);
        user = userRepository.save(user);
        return userMapper.mapUserToUpdateUserResponse(user);
    }

    private void updateUserFromRequestDto(UserUpdateRequest userUpdateRequest, User user) {
        if (userUpdateRequest.getUsername() != null) {
            user.setUsername(userUpdateRequest.getUsername());
        }

        if (userUpdateRequest.getEmail() != null) {
            user.setUsername(userUpdateRequest.getEmail());
        }

        if (userUpdateRequest.getPassword() != null) {
            user.setPassword(userUpdateRequest.getPassword());
        }
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
