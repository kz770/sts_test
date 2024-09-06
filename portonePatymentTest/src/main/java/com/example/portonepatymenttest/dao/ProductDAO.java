package com.example.portonepatymenttest.dao;

import com.example.portonepatymenttest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    @Query(value="select nvl(max(no),0)+1 from product", nativeQuery = true)
    public int getNextNo();

    public void deleteByNo(int no);

}
