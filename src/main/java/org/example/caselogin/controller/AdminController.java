package org.example.caselogin.controller;

import org.example.caselogin.model.ROLE;
import org.example.caselogin.model.User;
import org.example.caselogin.service.appUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AppUserService userService;

    @GetMapping("")
    public String adminHome() {
        return "/index";
    }

    @GetMapping("/add")
    public String adminDashboard(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Arrays.asList(ROLE.values()));
        return "addUserForm";
    }

    @PostMapping("/add")
    public String addUser(User user) {
        userService.save(user);
        return "redirect:/admin";
    }
}
