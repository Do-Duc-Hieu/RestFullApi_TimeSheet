package com.example.devTimesheet.service;


import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendEmail(String userName, String newPassword) throws MessagingException;
}
