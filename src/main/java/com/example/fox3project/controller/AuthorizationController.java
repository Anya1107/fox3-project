package com.example.fox3project.controller;

import com.example.fox3project.entity.dto.AuthRequest;
import com.example.fox3project.entity.dto.AuthResponse;
import com.example.fox3project.entity.dto.get.response.UserGetResponse;
import com.example.fox3project.security.jwt.JwtProvider;
import com.example.fox3project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping
    public AuthResponse auth(AuthRequest authRequest){
        UserGetResponse user = userService.findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponse(token);
    }
}
