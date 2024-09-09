package org.example.caselogin.controller;

import org.example.caselogin.model.ENUM.FEE_STATUS;
import org.example.caselogin.model.ENUM.ROLE;
import org.example.caselogin.model.Fee;
import org.example.caselogin.model.User;
import org.example.caselogin.repository.FeeRepository;
import org.example.caselogin.service.appUser.AppUserService;
import org.example.caselogin.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AppUserService userService;
    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private EmailService emailService;
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate1 = formatter.format(date);
    Date dueSoonDate = new Date(date.getTime() - 3L * 24 * 60 * 60 * 1000);
    String formattedDate2 = formatter.format(dueSoonDate);

    java.sql.Date sqlDate1 = java.sql.Date.valueOf(formattedDate1);
    java.sql.Date sqlDate2 = java.sql.Date.valueOf(formattedDate2);

    @GetMapping
    public String homePage() {
        return "adminPages/index";
    }

    @GetMapping("/dataClasses")
    public String dataClasses() {
        return "adminPages/charts/data";
    }

    @GetMapping("/moreInfo")
    public String adminDashboard(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Arrays.asList(ROLE.values()));
        return "adminPages/forms/add";
    }

    @PostMapping("/add")
    public String addUser(User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/fees")

    public void checkFeesAndSendReminders() {
        List<Fee> feesDueSoon = feeRepository.findFeesDueSoon(sqlDate2, sqlDate1);
        for (Fee fee : feesDueSoon) {
            if (fee.getStatus().equals(FEE_STATUS.TUITION.getRoleName())){
                String email = fee.getStudent().getUser().getEmail();
                String subject = "Hạn nộp học phí sắp đến!";
                String text = String.format("Chào %s, hạn nộp học phí của bạn là ngày %s. Vui lòng thanh toán trước ngày đó để tránh bị phạt.",
                        fee.getStudent().getUser().getFullName(), fee.getDueDate().toString());
                emailService.sendFeeReminderEmail(email, subject, text);
                System.out.println(fee);
            }
        }

    }
}
