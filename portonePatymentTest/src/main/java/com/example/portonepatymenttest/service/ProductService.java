package com.example.portonepatymenttest.service;

import com.example.portonepatymenttest.dao.ProductDAO;
import com.example.portonepatymenttest.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDAO dao;

    public List<Product> findAll() {
        return dao.findAll();
    }

    public Product save(Product product) {
        return dao.save(product);
    }

    public int getNextNo(){
        return dao.getNextNo();
    }

    public Product findById(int id) {
        return dao.findById(id).get();
    }

    public void delete(int no) {
        dao.deleteByNo(no);
    }


}
