package com.example.paymenttest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Member")
public class Member {
    @Id
    private String id;
    private String name;
    private String pwd;
    private String phone;
    private String email;
    private String addr;
    private String postnumber;
    private String role;
}
