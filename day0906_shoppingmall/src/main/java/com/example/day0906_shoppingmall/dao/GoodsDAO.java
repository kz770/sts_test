package com.example.day0906_shoppingmall.dao;

import com.example.day0906_shoppingmall.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDAO extends JpaRepository<Goods, Integer> {
}
