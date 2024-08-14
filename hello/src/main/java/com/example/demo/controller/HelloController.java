package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {
	@RequestMapping("/hello.do")
	public void hello(Model model) {
		model.addAttribute("msg","hello spring boot!!!");
	}
}
