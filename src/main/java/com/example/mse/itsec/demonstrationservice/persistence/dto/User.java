package com.example.mse.itsec.demonstrationservice.persistence.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "username must not be blank")
    @Size(min = 3, max = 100, message = "Username must be between 3 and 20 characters long")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @NotBlank(message = "e-mail address must not be blank")
    @Email(message = "e-mail address must be a in a valid format")
    @Column(name = "email")
    private String email;

}
