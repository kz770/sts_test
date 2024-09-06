package com.example.paymenttest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    private int no;
    private String imp_uid;
    private String merchant_uid;
    private int paid_amount;
    private String apply_num;
}
