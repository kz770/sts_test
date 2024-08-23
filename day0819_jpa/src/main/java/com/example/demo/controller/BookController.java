package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BookDAO;
import com.example.demo.service.BookService;
import com.example.demo.service.CustomerService;
import com.example.demo.vo.BookVO;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {

	@Autowired
	BookService service;
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/book/list")
	public void list(Model m,String sname, String searchColumn, String keyword,String op,  HttpSession session) {
		
		// 검색어가 ""가 오면 모두 검색시키기 위해 세션에 있는 검색어를 삭제한다
		// 그리고 검색어에 null을 설정한다
		if (keyword==null||keyword.equals("")) {	// 키워드가 null이기 때문에 뒤의 조건 판별 X
			session.removeAttribute("keyword");
			session.removeAttribute("searchColumn");
			session.removeAttribute("op");
			keyword=null;
			searchColumn=null;
			op=null;
		}
		
		// 검색 키워드가 없다면 세션에 저장된 검색어를 갖고온다
		if (keyword==null&&session.getAttribute("keyword")!=null) {
			keyword=(String)session.getAttribute("keyword");
			searchColumn=(String)session.getAttribute("searchColumn");
		}
		
		// 새로운 정렬 컬럼이 없고 세션에 저장된 정렬컬럼이 있다면 갖고온다
		if (sname==null&&session.getAttribute("sname")!=null) {
			sname=(String)session.getAttribute("sname");
		}
		
		List<BookVO> list=service.findAll(sname, keyword, searchColumn, op);
		m.addAttribute("list",list);
		
		// 검색어가 있다면 세션에 저장한다
		if (keyword!=null&&!keyword.equals("")) {
			session.setAttribute("keyword", keyword);
			session.setAttribute("searchColumn", searchColumn);
			session.setAttribute("op", op);
			
		}
		// 정렬을 했다면 세션에 저장한다
		if (sname!=null) {
			session.setAttribute("sname", sname);
		}
	}
	
	@PostMapping("/book/save")
	public String insertBook(BookVO vo) {
		service.save(vo);
		return "redirect:/book/list";
	}
	
	@GetMapping("/book/detail")
	public String detail(int bookid, Model m) {
		String view="/book/detail";
		try {
			BookVO vo = service.findByBookID(bookid);
			m.addAttribute("vo", vo);
		} catch (NoSuchElementException e) {
			view="/error";
			m.addAttribute("msg","해당 도서는 존재하지 않습니다."+bookid);
		}
		return view;
	}
//	@GetMapping("/book/detail")
//	public void detail(int bookid, Model m) {
//		BookVO vo = service.findByBookID(bookid);
//		m.addAttribute("vo", vo);
//	}
	
	@PostMapping("/book/update")
	public String update(BookVO vo) {
		service.save(vo);
		return "redirect:/book/list";
	}
	
	@GetMapping("/book/delete")
	public String delete(int bookid) {
		service.delete(bookid);
		return "redirect:/book/list";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}

}
