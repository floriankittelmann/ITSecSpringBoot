package com.example.mse.itsec.demonstrationservice.persistence;

import com.example.mse.itsec.demonstrationservice.persistence.dto.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;


    @Test
    void getUser() {
        Long userId = 1L;
        User user = userService.getUser(userId);

        assertThat(user.getUsername()).isEqualTo("user");
        assertThat(user.getPassword()).isEqualTo("password");
    }

    @Test
    void verifyCredentials() {
        User user = new User(null, "user", "password");

        boolean result = userService.verifyCredentials(user);

        assertThat(result).isTrue();

    }
}