package com.example.portonepatymenttest.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@Table(name="payment")
public class Payment {
    @Id
    // 순번
    private String imp_uid;
    // 결제방법
    private String pay_method;
    // 주문번호
    private String merchant_uid;
    //상품명
    //@ManyToMany
    private String goodsName;

//    @ManyToOne
//    @JoinColumn(name="ID")
//    private Customer customer;
    //고객아이디
    private String customerID;
    // 고객이메일
    private String customerEmail;
    // 고객이름
    private String customerName;
    // 고객전화
    private String customerPhone;
    // 고객주소
    private String customerAddr;
    // 고객우편번호
    private String customerPcode;


    // 상점 id
    private String storeId;

    // 카드 승인번호
    private String apply_num;

}
