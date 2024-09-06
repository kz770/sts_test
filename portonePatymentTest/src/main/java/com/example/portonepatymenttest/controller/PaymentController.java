package com.example.portonepatymenttest.controller;

import com.example.portonepatymenttest.dao.PaymentDAO;
import com.example.portonepatymenttest.entity.Customer;
import com.example.portonepatymenttest.entity.Payment;
import com.example.portonepatymenttest.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/purchase")
    public String purchase(Payment payment, HttpSession session){
        Customer customer = (Customer) session.getAttribute("loginUSER");
        payment.setCustomerID(customer.getId());
        payment.setCustomerEmail(customer.getEmail());
        payment.setCustomerName(customer.getName());
        payment.setCustomerPhone(customer.getPhone());
        payment.setCustomerAddr(customer.getAddress());
        payment.setCustomerPcode(customer.getPostCode());
        System.out.println(payment);
        paymentService.save(payment);
        return "redirect:/product/list";
    }

}
