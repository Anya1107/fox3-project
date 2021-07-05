package com.example.fox3project.service;

import com.example.fox3project.entity.User;
import com.example.fox3project.entity.dto.get.response.UserGetResponse;
import com.example.fox3project.entity.dto.update.request.UserUpdateRequest;
import com.example.fox3project.entity.dto.update.response.UserUpdateResponse;
import com.example.fox3project.exception.NotFoundException;
import com.example.fox3project.mapper.UserMapper;
import com.example.fox3project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserMapper userMapper;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void delete(long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id: " + id + " not found", "findById"));
        userRepository.delete(user);
    }

    public UserGetResponse findById(long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id: " + id + " not found", "findById"));
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
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id: " + id + " not found", "findById"));
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
}
