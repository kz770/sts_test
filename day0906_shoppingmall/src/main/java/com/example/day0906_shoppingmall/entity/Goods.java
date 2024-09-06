package com.example.day0906_shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "goods")
@Data
@SequenceGenerator(name = "SEQ_GOODS_GEN",
        sequenceName = "SEQ_GOODS",
        initialValue = 1, allocationSize = 1
)
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GOODS_GEN")
    private int no;
    private String item;
    private int qty;
    private int price;
    private String fname;

    @Transient
    private MultipartFile uploadFile;
}












