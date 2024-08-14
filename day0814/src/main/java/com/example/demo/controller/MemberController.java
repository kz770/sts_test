package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

import kr.co.youiwe.webservice.BitSms;

@Controller
public class MemberController {
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private BitSms sms;
	
	@Autowired
	private MemberDAO dao;
	
	@GetMapping("/join")
	public void joinForm() {		
	}
	
	@PostMapping("/join")
	@ResponseBody
	public String joinSubmit(MemberVO m) {
		dao.join(m);
		return "OK";
	}
	
	@GetMapping("/sendCode")
	@ResponseBody
	public String sendCode(String to, String authType) {//authType:phone,email
		String data = "";		
		Random r = new Random();
		int a = r.nextInt(10);int b = r.nextInt(10);int c = r.nextInt(10);int d = r.nextInt(10);
		data = a+""+b+""+c+""+d;		
		if(authType.equals("email")) {
			SimpleMailMessage mailMessage
			= new SimpleMailMessage();			
			mailMessage.setFrom("alice20240103@gmail.com");
			mailMessage.setTo(to);
			mailMessage.setSubject("인증코드 전송");	
			mailMessage.setText(data);			
			try {
				mailSender.send(mailMessage);
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}else {
			String from = "01025598279";
			sms.sendMsg(from,to, data);
		}		
		return data;
	}
}












