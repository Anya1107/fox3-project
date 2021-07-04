package com.example.fox3project.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;
    private List<String> roles;

    public AuthResponse(String token, String username, String email, List<String> roles) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
