package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllController {

	@GetMapping("/all/introduction")
	public void intro() {
		
	}
	@GetMapping("/all/location")
	public void loc() {
		
	}
}
