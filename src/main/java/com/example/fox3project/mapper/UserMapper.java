package com.example.fox3project.mapper;

import com.example.fox3project.entity.User;
import com.example.fox3project.entity.dto.add.request.UserCreateRequest;
import com.example.fox3project.entity.dto.add.response.UserCreateResponse;
import com.example.fox3project.entity.dto.get.response.UserGetResponse;
import com.example.fox3project.entity.dto.update.request.UserUpdateRequest;
import com.example.fox3project.entity.dto.update.response.UserUpdateResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class UserMapper {

    public User mapCreateUserRequestToUser(UserCreateRequest userCreateRequest){
        return User.builder()
                .username(userCreateRequest.getUsername())
                .email(userCreateRequest.getEmail())
                .password(userCreateRequest.getPassword())
                .age(userCreateRequest.getAge())
                .build();
    }

    public UserCreateResponse mapUserToUserCreateResponse(User user){
        return UserCreateResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .roles(user.getRoles())
                .build();
    }

    public User mapUpdateUserRequestToUser(UserUpdateRequest userUpdateRequest){
        return User.builder()
                .username(userUpdateRequest.getUsername())
                .email(userUpdateRequest.getEmail())
                .password(userUpdateRequest.getPassword())
                .age(userUpdateRequest.getAge())
                .build();
    }

    public UserUpdateResponse mapUserToUpdateUserResponse(User user){
        return UserUpdateResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }

    public UserGetResponse mapUserToUserGetResponse(User user){
        return UserGetResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .roles(user.getRoles())
                .build();
    }

    public List<UserGetResponse> mapUserListToGetUserResponseList(List<User> users){
        List<UserGetResponse> userGetResponses = new ArrayList<>();
        for (User user : users) {
            UserGetResponse userGetResponse = mapUserToUserGetResponse(user);
            userGetResponses.add(userGetResponse);
        }
        return userGetResponses;
    }
}
