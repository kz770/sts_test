package com.example.demo;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import kr.co.youiwe.webservice.BitSms;

@Configuration
public class SpringConfig {
	//JavaMailSenderImpl 생성
	@Bean
	public JavaMailSenderImpl javaMailSender() {
		JavaMailSenderImpl jms = new JavaMailSenderImpl();
		jms.setHost("smtp.gmail.com");
		jms.setPort(587); //GMAIL 포트버놓
		jms.setUsername("uhyeonjin0619");
		jms.setPassword("wrke vwgo ksza atoy");
		jms.setDefaultEncoding("UTF-8");
		Properties prop = new Properties();
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.ssl.checkserveridentity", true);
		prop.put("mail.smtp.ssl.trust", "*");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		jms.setJavaMailProperties(prop);
		return jms;
	}
	
	@Bean
	public BitSms sms() {
		return new BitSms();
	}
	
}


