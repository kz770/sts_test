package com.example.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="member")
public class MemberVO {
    @Id
    private String id;
    private String name;
    private String pwd;
    private String role;
}
