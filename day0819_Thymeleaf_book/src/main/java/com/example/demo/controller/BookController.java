package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.BookVO;

@Controller
public class BookController {
	@Autowired
	BookDAO dao;
	
	@GetMapping("/book/list")
	public void bookList(Model m) {
		List<BookVO> list=dao.findAll();
		m.addAttribute("list",list);
//		System.out.println(list);
	}
}
