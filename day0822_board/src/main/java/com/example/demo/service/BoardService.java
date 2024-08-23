package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

@Service
public class BoardService {
	@Autowired
	BoardDAO dao;
	
	public List<BoardVO> findAll(){
		return dao.findAll();
	}
}
