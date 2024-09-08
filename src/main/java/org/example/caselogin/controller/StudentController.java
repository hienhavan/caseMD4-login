package org.example.caselogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("")
    public String studentHome() {
        return "index2";
    }

    @GetMapping("/enrollments")
    public String studentEnrollments() {
        return "student/enrollments";
    }
}
