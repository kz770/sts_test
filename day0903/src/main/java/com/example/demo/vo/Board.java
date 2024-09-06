package com.example.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Entity
public class Board {
    @Id
    private int no;
    private String title;
    private String writer;
    private String content;
    private Date regdate;
    private String ip;
    private int hit;
    private String fname;
    private String fsize;
    private String pwd;
    private int b_ref;
    private int b_level;
    private int b_step;
    @Transient
    private MultipartFile uploadFile;
}
