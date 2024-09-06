package com.example.day0906_shoppingmall.dao;

import com.example.day0906_shoppingmall.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDAO extends JpaRepository<Cart, Integer> {

    // 아이디에 해당하는 고객이 담은 cart목록을 반환하는 메소드(쿼리메소드)
    // select * from cart where mid = 'kim';
    public List<Cart> findByMember_id(String id);
    
    // 아이디와 상품번호에 해당하는 레코드를 Cart에서 조회하여 반환하는 메소드
    // select * from cart where mid= 'kim' and gno=10;
    public Cart findByMember_idAndGoods_no(String id, int gno);
    
}











