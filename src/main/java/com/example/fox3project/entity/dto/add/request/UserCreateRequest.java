package com.example.fox3project.entity.dto.add.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {
    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @NotEmpty
    private int age;
}
