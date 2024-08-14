package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.youiwe.webservice.BitSms;

@RestController
public class EmpController {
	@Autowired
	private EmpDAO dao;
	
	@Autowired
	private MailSender mailSender;
	
	@GetMapping("/sendEmailPayroll")
	public String sendEmailPayroll(HttpServletRequest req) {
		List<EmpVO> list = dao.findAll();
		for(EmpVO e:list) {
			String ename= e.getEname();
			String email = e.getEmail();
			int salary = e.getSalary();
			int comm = e.getComm();
			int total = salary+comm;
			int month = (new Date()).getMonth()+1;
			String title = ename+"님의 급여명세서 입니다.[발신자:김은영]";			
			String text = ename+"님의 " + month +"월달 급여는 "+ total + "만원입니다.";
			
			SimpleMailMessage mailMessage
			= new SimpleMailMessage();
			
			mailMessage.setFrom("");
			mailMessage.setTo(email);
			mailMessage.setSubject(title);
			mailMessage.setText(text);
			
			try {
				mailSender.send(mailMessage);
				System.out.println(ename+"에게 메일 발송 완료");
			}catch (Exception ex) {
				System.out.println("예외발생:"+ex.getMessage());
			}
		}
		return "OK";
	}
}







