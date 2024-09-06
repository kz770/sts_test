package com.example.portonepatymenttest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="customer")
public class Customer {
    @Id
    private String id;
    private String name;
    private String pwd;
    private String phone;
    private String email;
    private String address;
    private String postCode;
    private String role;
}
