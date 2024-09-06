package com.example.portonepatymenttest.controller;

import com.example.portonepatymenttest.entity.Customer;
import com.example.portonepatymenttest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("/login")
    public void loginForm() {
    }

    @GetMapping("/join")
    public void joinForm() {
    }

    @PostMapping("/join")
    public String joinSubmit(Customer customer) {
        String view="";
        // 고객의 비밀번호를 암호화하여 저장한다
        customer.setPwd(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(customer.getPwd()));
        // 고객의 role 설정
        customer.setRole("user");
        System.out.println("customer = " + customer);
        Customer customerNew=service.save(customer);

        if(customerNew==null) {
            System.out.println("사용자가 정상적으로 등록되지 않았음");
            view = "redirect:/join";
        }else {
            view = "redirect:/login";
        }
        return view;
    }
}
