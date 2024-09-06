package com.example.day0906_shoppingmall.service;

import com.example.day0906_shoppingmall.dao.GoodsDAO;
import com.example.day0906_shoppingmall.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsDAO goodsDAO;
    public void insert(Goods g){
        goodsDAO.save(g);
    }
    public List<Goods> findAll(){
        return goodsDAO.findAll();
    }
    public Goods getGoods(int no){
        return goodsDAO.findById(no).get();
    }
}
