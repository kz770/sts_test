package com.example.paymenttest.controller;

import com.example.paymenttest.entity.Payment;
import com.example.paymenttest.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PayController {
    @Autowired
    private PaymentService paymentService;

    @ResponseBody
    @PostMapping("/payok")
    public String payok(Payment payment, Model model) {
//        payment.setNo(paymentService.getNextNo());
        paymentService.save(payment);
        return "ok";
    }
}
