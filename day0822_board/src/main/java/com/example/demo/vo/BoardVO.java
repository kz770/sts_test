package com.example.demo.vo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="board")
public class BoardVO {
	@Id
	private int no;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private String ip;
	private int hit;
	private String fname;
	private int fsize;
	private String pwd;
	private int b_ref;
	private int b_level;
	private int b_step;
	

}
