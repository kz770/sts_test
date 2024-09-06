package com.example.paymenttest.service;

import com.example.paymenttest.dao.PaymentDAO;
import com.example.paymenttest.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentDAO dao;

    public int getNextNo(){
        return dao.getNextNo();
    }
    public void save(Payment payment){
        payment.setNo(getNextNo());
        dao.save(payment);
    }
}

