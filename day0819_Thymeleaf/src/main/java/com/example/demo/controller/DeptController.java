package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.db.DeptDAO;
import com.example.demo.vo.DeptVO;

@Controller
public class DeptController {
	@Autowired
	private DeptDAO dao;
	
	@GetMapping("/deptList")
	public void list(Model m) {
		List<DeptVO> list= dao.findAll();
		System.out.println(list);
		m.addAttribute("list",list);
	}
}
