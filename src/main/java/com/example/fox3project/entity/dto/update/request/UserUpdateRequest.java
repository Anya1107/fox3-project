package com.example.fox3project.entity.dto.update.request;

import com.example.fox3project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    private String username;
    private String email;
    private String password;
    private int age;
    private Set<Role> roles;
}
