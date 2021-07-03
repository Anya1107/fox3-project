package com.example.fox3project.security;

import com.example.fox3project.entity.User;
import com.example.fox3project.entity.dto.get.response.UserGetResponse;
import com.example.fox3project.mapper.UserMapper;
import com.example.fox3project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;
    private UserMapper userMapper;

    @Override
    public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserGetResponse userGetResponse = userService.findByLogin(login);
        User user = userMapper.mapUserGetResponseToUser(userGetResponse);
        return CustomUserDetails.mapUserToCustomUserDetails(user);
    }
}
