package com.example.mse.itsec.demonstrationservice.resources;

import com.example.mse.itsec.demonstrationservice.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
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