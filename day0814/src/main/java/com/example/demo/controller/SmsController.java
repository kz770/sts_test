package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.youiwe.webservice.BitSms;

@Controller
public class SmsController {
	@Autowired
	private BitSms sms;
	
	@GetMapping("/sendSms")
	@ResponseBody
	public String sendSms(HttpServletRequest req) {
		String from = "01025598279";
		String to = "01085807247";
		String msg = "안녕[김은영]";
		sms.sendMsg(from, to, msg);
		return "OK";
	}
}







