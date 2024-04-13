package com.techtalk.techtalkapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleMail(String email, String token) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setText(prepareURL(token));
            mailMessage.setSubject("Password Forgot Redirect Link");
            javaMailSender.send(mailMessage);
            return "Şifre sıfırlama maili "+email+" adresine gönderildi. Spam kutunuzu kontrol etmeyi unutmayın";
        }

        catch (Exception e) {
            return null;
        }
    }
    private String prepareURL(String token){
        return ("http://localhost:8081/sifre_sıfırlama?token="+token);
    }
}
