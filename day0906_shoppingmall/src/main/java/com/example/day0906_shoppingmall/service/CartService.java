package com.example.day0906_shoppingmall.service;

import com.example.day0906_shoppingmall.dao.CartDAO;
import com.example.day0906_shoppingmall.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartDAO cartDAO;

    //회원의 장바구니 목록을 반환하는 메소드
    public List<Cart> listCart(String id){
        return cartDAO.findByMember_id(id);
    }

    //Cart에 등록하는 메소드
    public void insert(Cart c){
        //일단 수량을 한개로 설정한다.
        c.setQty(1);
        //카트에 담긴 상품번호와 회원아이디를 받아 온다.
        String uid = c.getMember().getId();
        int gno= c.getGoods().getNo();
        //그 상품이 이미 카트에 담겨있다면 갖고 온다.
        Cart old = cartDAO.findByMember_idAndGoods_no(uid, gno);
        //이미 그 상품이 있으면 수량을 1증가 시키고 그 카트번호를 설정한다.
        if(old != null){
            c.setQty(old.getQty()+1);
            c.setNo(old.getNo());
        }
        cartDAO.save(c);
    }
}














