package com.example.demo.controller;

import java.io.FileWriter;
import java.sql.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ContollerAspect {
	@Pointcut("execution (public * com.example.demo.controller..*(..))")
	public void controller() {

	}

	@Around("controller()")
	public Object pro(ProceedingJoinPoint joinPoint) {
//		System.out.println("테스트★☆★☆★☆★☆★☆");
		Object re = null;
		String uri = "";
		String ip = "";
		long start = System.currentTimeMillis();
		Date date = new Date(start);
		String time = date.toLocaleString();
		
		//타깃의 매개변수 목록을 갖고 옵니다.
		Object[] args = joinPoint.getArgs();
		if(args != null) {
			HttpServletRequest request = (HttpServletRequest)args[0];
			uri = request.getRequestURI();
			ip = request.getRemoteAddr();
		}
		
		try {
			re = joinPoint.proceed();
		} catch (Throwable e) {
		}
		
		long end = System.currentTimeMillis();
		long delay = end-start;
		String row = uri+"/"+ip+"/"+time+"/"+delay+"\n";
		System.out.println(row);
		try {
			int year = date.getYear()+1900;
			int month = date.getMonth()+1;
			int day = date.getDate();
			String fname = "c:/kosta_log/";
			fname += year;
			if(month<10) {
				fname+="0";
			}
			fname+=month;
			if(day<10) {
				fname+="0";
			}
			fname+=day;
			fname+=".txt";
			FileWriter fw = new FileWriter(fname, true);
			fw.write(row);
			fw.close();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		return re;
	}
}
