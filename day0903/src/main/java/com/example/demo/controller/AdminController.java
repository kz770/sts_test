package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/menu1")
    public String adminMenu1(HttpSession session) {
        System.out.println("member : "+session.getAttribute("member"));
        return "/admin/menu1";
    }
}
