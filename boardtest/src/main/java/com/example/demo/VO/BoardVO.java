package com.example.demo.VO;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
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
	private MultipartFile uploadFile;

}
