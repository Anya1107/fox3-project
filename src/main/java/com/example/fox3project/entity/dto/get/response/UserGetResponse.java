package com.example.fox3project.entity.dto.get.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetResponse {
    private long id;
    private String login;
    private String password;
    private int age;
}
