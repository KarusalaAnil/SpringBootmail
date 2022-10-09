package com.springbootmail.service;

import com.springbootmail.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendSimpleMail(EmailRequest request){

        SimpleMailMessage mail =new SimpleMailMessage();
        mail.setFrom(sender);
        mail.setTo(request.getToEmail());
        mail.setSubject(request.getSubject());
        mail.setText(request.getMessageBody());

        javaMailSender.send(mail);

        return "Email successfully send to :"+request.getToEmail();

    }
    public String sendMailWithAttachment(EmailRequest request) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(request.getToMailsList());
        mimeMessageHelper.setSubject(request.getSubject());
        mimeMessageHelper.setText(request.getMessageBody());

        FileSystemResource file =new FileSystemResource(request.getAttachment());
        mimeMessageHelper.addAttachment(file.getFilename(),file);

        javaMailSender.send(mimeMessage);

        return  "Mail send successfully with attachment :"+file.getFilename();



    }
}
