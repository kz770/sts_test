package com.example.portonepatymenttest.service;

import com.example.portonepatymenttest.dao.PaymentDAO;
import com.example.portonepatymenttest.entity.Payment;
import com.example.portonepatymenttest.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class PaymentService {
    @Autowired
    private PaymentDAO paymentDAO;
    public void save(Payment payment) {
        paymentDAO.save(payment);
    }
}
