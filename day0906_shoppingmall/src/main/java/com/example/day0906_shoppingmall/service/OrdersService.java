package com.example.day0906_shoppingmall.service;

import com.example.day0906_shoppingmall.dao.OrdersDAO;
import com.example.day0906_shoppingmall.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    @Autowired
    private OrdersDAO ordersDAO;

    public int getNextNo(){
        return ordersDAO.getNextNo();
    }

    public int insert(Orders o){
        int no = getNextNo();
        o.setNo(no);
        ordersDAO.save(o);
        return no;
    }
}
