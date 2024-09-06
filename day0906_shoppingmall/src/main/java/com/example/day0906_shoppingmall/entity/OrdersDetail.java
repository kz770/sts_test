package com.example.day0906_shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ordersdetail")
@Data
@SequenceGenerator(name = "SEQ_DETAIL_GEN",
        sequenceName = "SEQ_DETAIL",
        initialValue = 1, allocationSize = 1)
public class OrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DETAIL_GEN")
    private int no;

    @ManyToOne
    @JoinColumn(name = "ono", insertable = true, updatable = true)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "gno", insertable = true, updatable = true)
    private Goods goods;

    private int qty;
}













