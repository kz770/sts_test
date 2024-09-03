package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.MemberDAO;
import com.example.demo.db.DBManager;

@Controller
public class BookController {
	@Autowired
	DBManager manager;

	@GetMapping("/listBook")
	public void list(HttpSession session) {
		//인증(로그인)된 회원의 정보를 갖고오기 위하여
		// 먼저 시큐리티 인증객체가 필요하다.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		// 이 인증 객체를 통하여 인증된(로그인된) User객체를 받아온다.
		User user=(User)auth.getPrincipal();
		
		//이 인증된 User를 통해서 로그인 한 회원의 아이디 갖고오기
		String id = user.getUsername();	// 시큐리티의 인증이기 때문에 username으로 사용
		
		// 이 정보를 세션에 정장하고 뷰 페이지에서 출력하도록 한다.
		session.setAttribute("id", id);
		
		// 만약 유저의 전체 정보를 불러오고 싶다면?
		session.setAttribute("member", manager.findByID(id));
		System.out.println("user id : "+id);
	}
}
