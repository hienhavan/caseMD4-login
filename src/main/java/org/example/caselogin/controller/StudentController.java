package org.example.caselogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("")
    public String studentHome() {
        return "student/home"; // Trả về trang home cho student
    }

    @GetMapping("/enrollments")
    public String studentEnrollments() {
        return "student/enrollments"; // Trả về trang enrollments cho student
    }
}
