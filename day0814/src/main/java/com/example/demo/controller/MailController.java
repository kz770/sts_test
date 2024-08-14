package com.example.demo.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;

@Controller
@Setter
public class MailController {

//	@Autowired
//	private MailSender mailSender;

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("/sendHTML")
	@ResponseBody
	public String sendHTML() {
		mailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,"UTF-8");
				
				String text="<html>";
				text += "<h2>내가 살면서 인생에서 중요하게 여기는 것</h2>";
				text += "<ul>";
				text += "<li>돈</li>";
				text += "<li>건강</li>";
				text += "<li>가족</li>";
				text += "</ul>";
				text += "</html>";
				
				message.setFrom("uhyeonjin0619@gmail.com");
				message.setTo("jieen13@naver.com");
				message.setSubject("네이버가 g메일로 메일이 갑니까?");
				message.setText(text, true);
				message.addInline("myBall", new ClassPathResource("images/zzang1.png"));
				message.addAttachment("hello.txt", new ClassPathResource("docs/hello.txt"));
			}
		});
		return "OK";
	}

	@GetMapping("/sendMail")
	@ResponseBody
	public String send() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setFrom("");
		mailMessage.setTo("");
		mailMessage.setSubject("메일 보내기 연습");
//		mailMessage.setText("메일이 잘 갑니까?");

		Random r = new Random();
		int a = r.nextInt(10);
		int b = r.nextInt(10);
		int c = r.nextInt(10);
		int d = r.nextInt(10);
		String data = a + "" + b + "" + c + "" + d;
		mailMessage.setText(data);

		try {
			mailSender.send(mailMessage);
		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}

		return "OK";
	}
}
