package com.example.mse.itsec.demonstrationservice.persistence;

import com.example.mse.itsec.demonstrationservice.persistence.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);
    boolean existsUserByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User u WHERE u.username = :name and u.password= :password")
    Optional<User> findUserByUsernameAndPassword(@Param("name") String userStatus,
                                                 @Param("password") String userName);
}
