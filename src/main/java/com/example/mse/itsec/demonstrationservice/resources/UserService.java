package com.example.mse.itsec.demonstrationservice.resources;

import com.example.mse.itsec.demonstrationservice.dto.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public class UserService {

    private UserRepository userRepository;

    public User getUser(Long id) {
       return userRepository.getById(id);
    }

    public boolean verifyCredentials(User user) {
        return userRepository.exists(Example.of(user));
    }
}
