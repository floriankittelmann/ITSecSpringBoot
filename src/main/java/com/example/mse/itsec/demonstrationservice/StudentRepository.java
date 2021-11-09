package com.example.mse.itsec.demonstrationservice;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    @Query("select u from Student u where u.firstName = :firstname")
    List<Student> findByFirstnameQuery(@Param("firstname") String firstname);

    Student findById(long id);
}
