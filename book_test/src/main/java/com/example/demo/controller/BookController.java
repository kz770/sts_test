package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.DAO.BookDAO;

@Controller
public class BookController {
	
	@Autowired
	private BookDAO dao; //내부적으로 by type으로 의존관계 설정
	
	@RequestMapping("/listBook.do")
	public void list(Model model) {
		model.addAttribute("list",dao.findAll());
	
	}
}
