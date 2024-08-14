package com.example.demo.util;

import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

@Component
@EnableScheduling
public class EmpSchedule {
	
	@Autowired
	private EmpDAO dao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmpUtil util;
	
	@Scheduled(cron = "30 46 16 13 8 ?")
	public void sendEmail() {
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
	}
	
	
	//@Scheduled(cron=“초 분 시간 일 월 요일 연도")   
	// 1 2 3 4 5 6 7
	
	
	@Scheduled(cron="5 9 12 14 * ?")
	public void sendSalary() {
		
		List<EmpVO> list=dao.findAll();
		for(EmpVO e:list) {
			String text = util.getHtmlEmp(e);
			mailSender.send(new MimeMessagePreparator() {
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					// TODO Auto-generated method stub
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,"UTF-8");
					
					message.setFrom("uhyeonjin0619@gmail.com");
					message.setTo(e.getEmail());
					message.setSubject("급여명세서 [발신자:유현진]");
					message.setText(text, true);
				}
			});
			System.out.println(e.getEname()+   "에게 메일발송 완료");
			
		}
		
	
		
		
		
//		List<EmpVO> list=dao.findAll();
//		for(EmpVO e:list) {
//			String text=new EmpUtil().getHtmlEmp(e);
//			mailSender.send(new MimeMessagePreparator() {
//				
//				@Override
//				public void prepare(MimeMessage mimeMessage) throws Exception {
//					// TODO Auto-generated method stub
//					MimeMessageHelper message= new MimeMessageHelper(mimeMessage,"UTF-8");
//					
//					message.setFrom("uhyeonjin0619@gmail.com");
//					message.setTo(e.getEmail());
//					message.setSubject("급여명세서 [발신자:유현진]");
//					message.setText(text, true);
//				}
//			});
//			System.out.println(e.getEname()+"보냄");
//		}
//		
	}
	
	
	
		
	@Scheduled(cron = "30 26 15 * * ?")
//	@Scheduled(fixedRate = 10000)
	public void pro() {
		System.out.println("박규희 화이팅!");
		System.out.println("공희상 화이팅!");
		System.out.println("---------------------------------------");
	}
}
