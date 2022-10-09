package com.springbootmail.controller;

import com.springbootmail.dto.EmailRequest;
import com.springbootmail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class NotificationController {

    @Autowired
    private EmailService service;

    @PostMapping
    public String sendMail(@RequestBody EmailRequest request) {
        return service.sendSimpleMail(request);
    }

    @PostMapping("/sendMails")
    public String sendMails(@RequestBody EmailRequest request) throws MessagingException {
        return service.sendMailWithAttachment(request);
    }
}
