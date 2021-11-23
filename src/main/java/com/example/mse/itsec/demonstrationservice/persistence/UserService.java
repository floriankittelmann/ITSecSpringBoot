package com.example.mse.itsec.demonstrationservice.persistence;

import com.example.mse.itsec.demonstrationservice.persistence.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
       return userRepository.getById(id);
    }

    public boolean verifyCredentials(User user) {
        return userRepository.existsUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public User getUser(String name) {
        return  userRepository.getByUsername(name);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
