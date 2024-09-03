package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

@SpringBootApplication
public class Day0902SecurityMybitisApplication {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// 사용자가 입력한 비밀번호를 인코딩 하기 위해서 요구된다
	}
	
	public static void main(String[] args) {
		
//		MemberVO kim=new MemberVO();
//		kim.setId("kim");
//		kim.setPwd(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("kim"));
//		// 인코딩 과정을 거친다
//		kim.setName("spring kim");
//		kim.setRole("user");
//		
//		MemberVO lee = new MemberVO();
//		lee.setId("lee");
//		lee.setPwd(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("lee"));
//		lee.setName("summer");
//		lee.setRole("admin");
//		
//		DBManager.insert(kim);
//		DBManager.insert(lee);
//		System.out.println("2명의 계정을 등록함");
		SpringApplication.run(Day0902SecurityMybitisApplication.class, args);
	}

}
