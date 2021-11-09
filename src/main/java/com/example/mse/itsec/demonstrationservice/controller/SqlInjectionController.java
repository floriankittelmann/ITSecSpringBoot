package com.example.mse.itsec.demonstrationservice.controller;

import com.example.mse.itsec.demonstrationservice.persistence.UserService;
import com.example.mse.itsec.demonstrationservice.persistence.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.time.ZonedDateTime;
import java.util.List;

@Controller
public class SqlInjectionController {

    private UserService userService;
    private EntityManager entityManager;

    @Autowired
    public SqlInjectionController(UserService userService, EntityManager entityManager) {
        this.userService = userService;
        this.entityManager = entityManager;
    }

    @GetMapping("/sqli/protected")
    public String login_protected(@RequestParam(name="name") String name, @RequestParam(name="password") String password,
                           Model model) {
        if(userService.verifyCredentials(new User(null, name, password))) {
            model.addAttribute("name", name);
            model.addAttribute("time", ZonedDateTime.now().toString());
            return "login";
        }

        return "failed";
    }

    @GetMapping("/sqli/vulnerable")
    public String login_vulnerable(@RequestParam(name="name") String name, @RequestParam(name="password") String password,
                           Model model) {
        List results = entityManager.createNativeQuery("Select * from users where username = '" + name + "' and password = '" + password+"'").getResultList();
        boolean credentialsMatch = !results.isEmpty();

        if(credentialsMatch) {
            model.addAttribute("name", name);
            model.addAttribute("time", ZonedDateTime.now().toString());
            return "login";
        }
        return "failed";

    }
}
