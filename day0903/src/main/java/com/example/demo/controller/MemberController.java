package com.example.demo.controller;

import com.example.demo.service.MemberService;
import com.example.demo.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberService service;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

//    @PostMapping("/login")
//    public String loginSubmit() {
//        String viewPage="redirect:/";
//        return viewPage;
//    }

    @GetMapping("/join")
    public void joinForm() {
    }

    @PostMapping("/join")
    public String join(MemberVO m) {
        System.out.println("member vo"+m);
        String viewPage = "redirect:/listBook";
        m.setPwd(passwordEncoder.encode(m.getPwd()));
        m.setRole("user");
        service.save(m);
        return viewPage;
    }
}
