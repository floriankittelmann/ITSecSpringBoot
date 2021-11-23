package com.example.mse.itsec.demonstrationservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProtectedController {

    @GetMapping("/protected")
    public String getProtected(Model model) {

        return "protected";
    }

    @GetMapping("/admin/settings")
    public String getSettings(Model model) {

        return "settings";
    }
}
