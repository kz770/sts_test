package com.example.portonepatymenttest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@Table(name="product")
public class Product {
    @Id
    private int no;
    private String name;
    private int price;
    private int qty;
    private String fname;
    @Transient
    private MultipartFile multipartFile;
}
