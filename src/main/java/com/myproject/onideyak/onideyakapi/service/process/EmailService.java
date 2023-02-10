package com.myproject.onideyak.onideyakapi.service.process;

import javax.mail.MessagingException;

public interface EmailService {
    boolean sendEmail(String to, String subject, String body) throws MessagingException;
}
