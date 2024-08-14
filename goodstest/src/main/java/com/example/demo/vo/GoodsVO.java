package com.example.demo.vo;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVO {
	private String no;
	private String item;
	private int price;
	private int qty;
	private String fname;
	private MultipartFile uploadFile;
}
