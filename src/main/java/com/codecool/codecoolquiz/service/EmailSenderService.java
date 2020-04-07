package com.codecool.codecoolquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    void sendEmail(String email, String username) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Welcome " + username + "!");
        msg.setText("Thank you for registering on our site. Enjoy the quizzes! :)");

        javaMailSender.send(msg);
    }
}
