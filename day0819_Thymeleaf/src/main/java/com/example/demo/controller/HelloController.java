package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController	//view로 가는게 아니라 data를 body로 응답하겠다
public class HelloController {

		@GetMapping("/hello")
		public String hello() {
			return "hello";	//hello라는 view를 주는게 아니라 hello를 응답
		}

}
