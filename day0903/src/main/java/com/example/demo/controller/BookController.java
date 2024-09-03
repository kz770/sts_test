package com.example.demo.controller;

import com.example.demo.service.MemberService;
import com.example.demo.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @Autowired
    private MemberService service;
    @GetMapping("/listBook")
    public void listBook(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        MemberVO m = service.findById(user.getUsername());
        System.out.println("m = " + m);
        session.setAttribute("member", m);
    }
}
