package com.crud.crudoperations.controller;
import com.crud.crudoperations.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/email")
    public class EmailController {

        @Autowired
        private EmailService emailService;

        @PostMapping("/send")
        public String sendMail(@RequestParam String to,
                               @RequestParam String subject,
                               @RequestParam String message) {
            emailService.sendEmail(to, subject, message);
            return "Email sent successfully to " + to;
        }
    }

