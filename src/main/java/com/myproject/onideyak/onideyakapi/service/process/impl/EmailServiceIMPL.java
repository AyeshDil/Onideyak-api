package com.myproject.onideyak.onideyakapi.service.process.impl;

import com.myproject.onideyak.onideyakapi.service.process.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class EmailServiceIMPL implements EmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String fromEmail;


    @Override
    public boolean sendEmail(String to, String subject, String body) throws MessagingException {

//        Simple Mail Message
/*
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
*/

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);

            mimeMessage.setFrom(new InternetAddress(fromEmail));
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, to);
            mimeMessage.setSubject(subject);
            /*File htmlContent = new File("Email-template.html");
            Scanner scanner = new Scanner(htmlContent);

            String fullContent = null;
            while (scanner.hasNextLine()){
                fullContent = scanner.nextLine();
            }
            mimeMessage.setContent(fullContent, "text/html; charset=utf-8");

            mailSender.send(mimeMessage);*/

            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(body, true);
            mimeMessageHelper.setSubject(subject);

            mailSender.send(mimeMessage);
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
