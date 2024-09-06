package com.example.day0906_shoppingmall.service;

import com.example.day0906_shoppingmall.dao.OrdersDetailDAO;
import com.example.day0906_shoppingmall.entity.OrdersDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersDetailService {
    @Autowired
    private OrdersDetailDAO dao;

    public void insert(OrdersDetail detail){
        dao.save(detail);
    }
}
