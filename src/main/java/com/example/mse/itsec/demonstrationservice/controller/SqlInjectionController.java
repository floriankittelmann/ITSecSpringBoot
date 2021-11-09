package com.example.mse.itsec.demonstrationservice.controller;

import com.example.mse.itsec.demonstrationservice.persistence.UserService;
import com.example.mse.itsec.demonstrationservice.persistence.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.ZonedDateTime;
import java.util.List;

@Controller
public class SqlInjectionController {

    private final UserService userService;
    private final EntityManager entityManager;

    @Autowired
    public SqlInjectionController(UserService userService, EntityManager entityManager) {
        this.userService = userService;
        this.entityManager = entityManager;
    }

    @GetMapping("/sqli/protected1")
    public String login_protected1(@RequestParam(name="name") String name, @RequestParam(name="password") String password,
                           Model model) {
        boolean credentialsValid = userService.verifyCredentials(new User(null, name, password));
        if(credentialsValid) {
            model.addAttribute("name", name);
            model.addAttribute("time", ZonedDateTime.now().toString());
            return "login";
        }

        return "failed";
    }
    @GetMapping("/sqli/protected2")
    public String login_protected2(@RequestParam(name="name") String name, @RequestParam(name="password") String password,
                                  Model model) {
        boolean credentialsValid = userService.verifyCredentialsNative(new User(null, name, password));
        if(credentialsValid) {
            model.addAttribute("name", name);
            model.addAttribute("time", ZonedDateTime.now().toString());
            return "login";
        }

        return "failed";
    }
    @GetMapping("/sqli/vulnerable1")
    public String login_vulnerable1(@RequestParam(name="name") String name, @RequestParam(name="password") String password,
                           Model model) {
        List results = entityManager.createNativeQuery("Select * from USER where username = '" + name + "' and password = '" + password+"'").getResultList();
        boolean credentialsMatch = !results.isEmpty();

        if(credentialsMatch) {
            model.addAttribute("name", name);
            model.addAttribute("time", ZonedDateTime.now().toString());
            return "login";
        }
        return "failed";

    }


    @GetMapping("/sqli/vulnerable2")
    public String login_vulnerable2(@RequestParam(name="name") String name, @RequestParam(name="password") String password,
                                   Model model) {

        String jql = "from User where username = '" + name + "'and password = '" + password+"'";
        TypedQuery<User> q = entityManager.createQuery(jql, User.class);
        boolean credentialsMatch  =  q.getResultList()
                .size() > 0;

        if(credentialsMatch) {
            model.addAttribute("name", name);
            model.addAttribute("time", ZonedDateTime.now().toString());
            return "login";
        }
        return "failed";

    }
}
