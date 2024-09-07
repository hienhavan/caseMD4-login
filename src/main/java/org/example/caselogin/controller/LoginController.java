package org.example.caselogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    // Trang đăng nhập
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Trang đăng ký
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // Trang lỗi 404
    @GetMapping("/error_404")
    public String error404() {
        return "error_404";
    }
}
