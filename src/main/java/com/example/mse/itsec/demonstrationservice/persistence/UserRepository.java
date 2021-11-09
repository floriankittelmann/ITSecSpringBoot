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

    // Using Spring Data Query DSL generates save queries by default
    boolean existsUserByUsernameAndPassword(String username, String password);

    // Native queries are as close to SQL we can get in Spring Boot repositories. We are forced to use parametrized queries
    @Query(value = "SELECT * FROM User u WHERE u.username = :name and u.password= :password", nativeQuery = true)
    Optional<User> findUserByUsernameAndPassword(@Param("name") String userStatus,
                                                 @Param("password") String userName);
}
