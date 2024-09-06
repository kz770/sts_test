package com.example.day0906_shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Orders {
    @Id
    private int no;                         //순번
    private String pay_method;              // 지불방식
    private String merchant_uid;            //주문번호
    private String name;                    //상품이름
    private int amount;                     //총주문금액

    @ManyToOne
    @JoinColumn(name = "mid", insertable = true, updatable = true) //회원아이디
    private Member member;

    private String imp_uid;                 //가맹점아이디
    private String apply_num;               //카드승인번호
}












