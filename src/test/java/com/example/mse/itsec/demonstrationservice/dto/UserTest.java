package com.example.mse.itsec.demonstrationservice.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    public void createUser() {
        User user = new User(1l,"user","password");

        assertThat(user.getId()).isEqualTo(1l);
        assertThat(user.getUsername()).isEqualTo("user");
        assertThat(user.getPassword()).isEqualTo("password");
    }
}