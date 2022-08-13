package com.surge.surgeAssessment.serviceImpl;

import com.surge.surgeAssessment.dto.UserDto;
import com.surge.surgeAssessment.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;
	 
	@Override
	public void sendSimpleMessage(String to, String subject, String body) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shaariqah.ismath@gmail.com");
        message.setTo(to);
        message.setSubject(subject); 
        message.setText(body);
        emailSender.send(message);
		
	}

}
