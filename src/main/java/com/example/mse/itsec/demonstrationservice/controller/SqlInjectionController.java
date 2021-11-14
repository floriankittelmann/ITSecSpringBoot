package com.example.mse.itsec.demonstrationservice.controller;

import com.example.mse.itsec.demonstrationservice.persistence.UserService;
import com.example.mse.itsec.demonstrationservice.persistence.dto.User_;
import com.example.mse.itsec.demonstrationservice.persistence.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @GetMapping("/sqli/derived_queries")
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
    @GetMapping("/sqli/named_queries")
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

    @GetMapping("/sqli/raw_vulnerable")
    public String login_vulnerable1(@RequestParam(name="name") String name, @RequestParam(name="password") String password,
                           Model model) {

        // Using string concatenation to create a query -> BAD!
        String sql = "select * from USER where username = '" + name + "' and password = '" + password+"'";
        List results = entityManager.createNativeQuery(sql).getResultList();
        boolean credentialsMatch = !results.isEmpty();

        if(credentialsMatch) {
            model.addAttribute("name", name);
            model.addAttribute("time", ZonedDateTime.now().toString());
            return "login";
        }
        return "failed";

    }


    @GetMapping("/sqli/jpql_vulnerable")
    public String login_vulnerable2(@RequestParam(name="name") String name, @RequestParam(name="password") String password,
                                   Model model) {

        // Using JPQL with string concatenation (unsafe) -> JPA/Hibenate does not help to prevent SQLi in this case.
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

    @GetMapping("/sqli/jpql_protected")
    public String jql_parametrized(@RequestParam(name="name") String name, @RequestParam(name="password") String password,
                                    Model model) {
        // Using JPQL with parameterized query (safe) -> JPA/Hibenate does not help to prevent SQLi in this case.
        String jql = "from User where username = :name and password = :password";
        TypedQuery<User> q = entityManager.createQuery(jql, User.class)
                .setParameter("name",name)
                .setParameter("password",password);

        boolean credentialsMatch  =  q.getResultList()
                .size() > 0;

        if(credentialsMatch) {
            model.addAttribute("name", name);
            model.addAttribute("time", ZonedDateTime.now().toString());
            return "login";
        }
        return "failed";
    }

    @GetMapping("/sqli/jpa_criteria_api")
    public String jpa_criteria_api(@RequestParam(name="name") String name, @RequestParam(name="password") String password, Model model) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);
        cq.select(root);
        cq.where(
                cb.equal(root.get(User_.username), name),
                cb.equal(root.get(User_.password), password)
        );
        TypedQuery<User> q = entityManager.createQuery(cq);
        boolean credentialsMatch = q.getResultList().size() > 0;

        if(credentialsMatch) {
            model.addAttribute("name", name);
            model.addAttribute("time", ZonedDateTime.now().toString());
            return "login";
        }
        return "failed";
    }
}
