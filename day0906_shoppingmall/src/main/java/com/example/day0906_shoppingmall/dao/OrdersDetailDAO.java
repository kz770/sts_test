package com.example.day0906_shoppingmall.dao;

import com.example.day0906_shoppingmall.entity.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDetailDAO extends JpaRepository<OrdersDetail, Integer> {

}
