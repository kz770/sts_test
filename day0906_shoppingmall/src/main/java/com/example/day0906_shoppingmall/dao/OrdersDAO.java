package com.example.day0906_shoppingmall.dao;

import com.example.day0906_shoppingmall.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDAO extends JpaRepository<Orders, Integer> {

    @Query(value = "select nvl(max(no),0)+1 from orders", nativeQuery = true)
    public int getNextNo();
}
