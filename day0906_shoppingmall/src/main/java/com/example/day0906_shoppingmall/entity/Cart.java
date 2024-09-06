package com.example.day0906_shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart")
@Data
@SequenceGenerator(name = "SEQ_CART_GEN",
        sequenceName = "SEQ_CART",
        initialValue = 1, allocationSize = 1
)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CART_GEN")
    private int no;

    @ManyToOne
    @JoinColumn(name = "mid", insertable = true, updatable = true)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "gno", insertable = true, updatable = true)
    private Goods goods;

    private int qty;
}








