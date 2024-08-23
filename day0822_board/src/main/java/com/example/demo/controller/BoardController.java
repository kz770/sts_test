package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService service;
	
	@GetMapping("/board/list")
	public void list(Model m) {
		m.addAttribute("list",service.findAll());
	}
}
