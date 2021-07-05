package com.example.fox3project.repository;

import com.example.fox3project.entity.ERole;
import com.example.fox3project.entity.Role;
import com.example.fox3project.entity.User;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @BeforeAll
    public void testCreate() {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1L, ERole.ROLE_USER));
        user.setId(6L);
        user.setUsername("user");
        user.setEmail("user@mail.ru");
        user.setPassword("user");
        user.setAge(123);
        user.setRoles(roles);
        userRepository.save(user);
        assertTrue(userRepository.existsByUsername("user"));
    }

    @Test
    public void testFindAll() {
        List<User> users = userRepository.findAll();
        assertThat(users).size().isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        User user = userRepository.findById(6L).get();
        assertEquals("user", user.getUsername());
    }

    @Test
    public void testUpdate() {
        User user = userRepository.findById(6L).get();
        user.setAge(111);
        userRepository.save(user);
        assertNotEquals(123, userRepository.findByUsername("user").get().getAge());
    }
}
