package com.example.mse.itsec.demonstrationservice.controller;

import com.example.mse.itsec.demonstrationservice.persistence.UserService;
import com.example.mse.itsec.demonstrationservice.persistence.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid User user, Model model) {
        userService.save(user);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "registersuccess";
    }

    @PostMapping("/register_xss")
    public String registerUserXss(@ModelAttribute User user, Model model) {
        userService.save(user);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "registersuccess_xss";
    }

    @GetMapping("/register")
    public String getForm(Model model) {
        model.addAttribute("user", new User());
        return "registerform";
    }

}
