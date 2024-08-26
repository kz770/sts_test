package com.example.demo.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@GetMapping("/board/list")
	public void list(Model model) {
		model.addAttribute("list", boardService.findAll());
	}
	
	@GetMapping("/board/detail/{no}")
	public String detail(@PathVariable("no") int no, Model model) {
		String view = "/board/detail";
		try {		
			model.addAttribute("b", boardService.findById(no));
		}catch (NoSuchElementException e) {
			view = "/error";
			model.addAttribute("msg", "해당 게시물이 존재하지 않습니다. "+ no);
		}		
		return view;
	}
}
