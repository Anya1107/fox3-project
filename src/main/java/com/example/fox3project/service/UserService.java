package com.example.fox3project.service;

import com.example.fox3project.entity.Role;
import com.example.fox3project.entity.User;
import com.example.fox3project.entity.dto.add.request.UserCreateRequest;
import com.example.fox3project.entity.dto.add.response.UserCreateResponse;
import com.example.fox3project.entity.dto.get.response.UserGetResponse;
import com.example.fox3project.entity.dto.update.request.UserUpdateRequest;
import com.example.fox3project.entity.dto.update.response.UserUpdateResponse;
import com.example.fox3project.mapper.UserMapper;
import com.example.fox3project.repository.RoleRepository;
import com.example.fox3project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final EntityManager entityManager;

    @Transactional
    public UserCreateResponse add(UserCreateRequest userCreateRequest) {
        Role role = roleRepository.findByName("ROLE_USER");
        User user = userMapper.mapCreateUserRequestToUser(userCreateRequest);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        user = userRepository.save(user);
        return userMapper.mapUserToUserCreateResponse(user);
    }

    @Transactional
    public void delete(long id) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        userRepository.delete(user);
    }

    public UserGetResponse findById(long id) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        return userMapper.mapUserToUserGetResponse(user);
    }

    public UserGetResponse findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        return userMapper.mapUserToUserGetResponse(user);
    }

    public UserGetResponse findByLoginAndPassword(String login, String password) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return userMapper.mapUserToUserGetResponse(user);
            }
        }
        return null;
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
        if (userUpdateRequest.getLogin() != null) {
            user.setLogin(userUpdateRequest.getLogin());
        }

        if (userUpdateRequest.getPassword() != null) {
            user.setPassword(userUpdateRequest.getPassword());
        }
    }
}
