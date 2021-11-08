package com.example.mse.itsec.demonstrationservice.resources;

import com.example.mse.itsec.demonstrationservice.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<User, Long> {

}
