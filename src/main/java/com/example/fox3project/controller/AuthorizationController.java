package com.example.fox3project.controller;

import com.example.fox3project.entity.dto.AuthRequest;
import com.example.fox3project.entity.dto.AuthResponse;
import com.example.fox3project.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorization;

    @PostMapping
    public AuthResponse auth(@RequestBody AuthRequest authRequest){
        return authorization.authorization(authRequest);
    }
}
