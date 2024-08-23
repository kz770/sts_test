package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.CustomerService;
import com.example.demo.vo.CustomerVO;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@GetMapping("/customer/list")
	public void customerList(Model m) {
		m.addAttribute("list",service.findAll());
	}
	
	@PostMapping("/customer/save")
	public String insert(CustomerVO vo) {
		service.save(vo);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/customer/insert")
	public void insert(Model m) {
		m.addAttribute("custid",service.getNextCustid());
		// 새로운 고객을 등록하는 폼을 요청하면
		// 새로운 고객번호를 발행한다
	}
	
	// customer 수정 메서드
	@GetMapping("/customer/update")
	public void update(int custid, Model m) {
		CustomerVO vo=service.findById(custid);
		m.addAttribute("vo",vo);
	}
	
	@PostMapping("/customer/update")
	public String update_complete(CustomerVO vo) {
		service.save(vo);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/customer/delete")
	public String delete(int custid) {
		service.deleteById(custid);
		return "redirect:/customer/list";
	}
	
	
}
