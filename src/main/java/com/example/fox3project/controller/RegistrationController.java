package com.example.fox3project.controller;

import com.example.fox3project.entity.dto.add.request.UserCreateRequest;
import com.example.fox3project.entity.dto.add.response.UserCreateResponse;
import com.example.fox3project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reg")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping
    public UserCreateResponse add(@RequestBody UserCreateRequest userCreateRequest){
        return userService.add(userCreateRequest);
    }
}
