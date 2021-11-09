package com.example.mse.itsec.demonstrationservice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
public class GreetingController {

    @Autowired StudentRepository studentRepository;


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/sqlinjection/possible")
    public String sqlinjectionpossible(@RequestParam(name="firstname", required=true) String firstname, Model model) {
        List<Student> result = studentRepository.findByFirstName(firstname);
        model.addAttribute("students", result);
        return "sqlinjectionpossible";
    }

    @GetMapping("/sqlinjection/notpossible")
    public String sqlinjectionnotpossible(@RequestParam(name="firstname", required=true) String firstname, Model model) {
        List<Student> result = studentRepository.findByFirstName(firstname);
        List<Student> resultQuery = studentRepository.findByFirstnameQuery(firstname);
        model.addAttribute("students", result);
        model.addAttribute("studentsQuery", resultQuery);
        return "sqlinjectionnotpossible";
    }

    @GetMapping("/loaddata")
    public String loaddata(Model model) {
        studentRepository.deleteAll();
        studentRepository.save(new Student("Jack", "Bauer", "1234"));
        studentRepository.save(new Student("Chloe", "O'Brian", "cool"));
        studentRepository.save(new Student("Kim", "Bauer", "test"));
        studentRepository.save(new Student("David", "Palmer", "root"));
        studentRepository.save(new Student("Michelle", "Dessler", "admin"));
        studentRepository.save(new Student("Test", "test", "admin"));
        model.addAttribute("students", studentRepository.findAll());
        return "loaddata";
    }
}
