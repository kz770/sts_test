package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import com.example.demo.service.BookService;
import com.example.demo.service.BookService2;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrdersService;
import com.example.demo.vo.CustomerVO;
import com.example.demo.vo.OrdersVO;

@Controller
public class OrderController {
	@Autowired
	CustomerService cService;
	@Autowired
	OrdersService oService;
	@Autowired
	BookService bService;
	
	@GetMapping("/orders/insert")
	public void insert(Model m) {
		m.addAttribute("orderid",oService.getNextOrderId());
		m.addAttribute("cList",cService.findAll());
		m.addAttribute("bList",bService.findAll(null,null,null,null));
	}
	
	@PostMapping("/orders/insert")
	public String insertComplete(OrdersVO vo) {
		vo.setCustomer(cService.findById(vo.getCustomer().getCustid()));
		vo.setBook(bService.findByBookID(vo.getBook().getBookid()));
		oService.insert(vo);
		return "redirect:/";
	}
	
	@GetMapping("/orders/list")
	public void orderList(Model m, String keyword) {
		
		m.addAttribute("list",oService.findAll(keyword));
	}
	
	
}
