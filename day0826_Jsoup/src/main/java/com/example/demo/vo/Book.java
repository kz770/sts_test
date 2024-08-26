package com.example.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="book")
public class Book {
	@Id
	private String p_code;
	private String title;
	private String writer;
	private String regdate;
	private int price;
}
