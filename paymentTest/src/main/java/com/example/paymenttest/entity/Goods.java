package com.example.paymenttest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="goods")
public class Goods {
    @Id
    private int no;
    private String name;
    private double price;
}
