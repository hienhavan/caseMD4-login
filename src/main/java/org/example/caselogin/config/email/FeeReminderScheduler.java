package org.example.caselogin.config.email;

import org.example.caselogin.model.Fee;
import org.example.caselogin.repository.FeeRepository;
import org.example.caselogin.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class FeeReminderScheduler {

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private EmailService emailService;
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate1 = formatter.format(date);
    Date dueSoonDate = new Date(date.getTime() + 3L * 24 * 60 * 60 * 1000);
    String formattedDate2 = formatter.format(dueSoonDate);

    java.sql.Date sqlDate1 = java.sql.Date.valueOf(formattedDate1);
    java.sql.Date sqlDate2 = java.sql.Date.valueOf(formattedDate2);

    @Scheduled(cron = "50 23 * * * *")
    public void checkFeesAndSendReminders() {
        List<Fee> feesDueSoon = feeRepository.findFeesDueSoon(sqlDate1, sqlDate2);
        for (Fee fee : feesDueSoon) {
            String email = fee.getStudent().getUser().getEmail();
            String subject = "Hạn nộp học phí sắp đến!";
            String text = String.format("Chào %s, hạn nộp học phí của bạn là ngày %s. Vui lòng thanh toán trước ngày đó để tránh bị phạt.",
                    fee.getStudent().getUser().getFullName(), fee.getDueDate().toString());
            emailService.sendFeeReminderEmail(email, subject, text);
        }
    }
}
