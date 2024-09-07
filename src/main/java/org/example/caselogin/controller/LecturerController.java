package org.example.caselogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    @GetMapping("")
    public String lecturerHome() {
        return "/a";
    }

    @GetMapping("/courses")
    public String lecturerCourses() {
        return "lecturer/courses";
    }
}
