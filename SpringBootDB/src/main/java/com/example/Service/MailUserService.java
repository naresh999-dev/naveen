package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailUserService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public void snedMail(String toEmail,String message) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Activation Code");
        mailMessage.setText(message);
        mailMessage.setFrom("sainavi8999@gmail.com");
        javaMailSender.send(mailMessage);
	}
	

}
