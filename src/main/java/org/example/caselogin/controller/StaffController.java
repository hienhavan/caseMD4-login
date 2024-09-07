package org.example.caselogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("")
    public String staffHome() {
        return "staff/home"; // Trả về trang home cho staff
    }

    @GetMapping("/reports")
    public String staffReports() {
        return "staff/reports"; // Trả về trang reports cho staff
    }
}