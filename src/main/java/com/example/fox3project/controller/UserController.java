package com.example.fox3project.controller;

import com.example.fox3project.entity.dto.get.response.UserGetResponse;
import com.example.fox3project.entity.dto.update.request.UserUpdateRequest;
import com.example.fox3project.entity.dto.update.response.UserUpdateResponse;
import com.example.fox3project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public UserGetResponse findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PutMapping(path = "/{id}")
    public UserUpdateResponse update(@PathVariable long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(id, userUpdateRequest);
    }

    @GetMapping
    public List<UserGetResponse> findAll(Pageable pageable) {
        return userService.findAll(pageable);
    }
}
