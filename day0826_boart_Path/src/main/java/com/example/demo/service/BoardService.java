package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.entity.Board;

@Service
public class BoardService {
	@Autowired
	private BoardDAO dao;
	
	public List<Board> findAll(){
//		return dao.findAll();
		return dao.selectAll();
	}
	
	public int getNextNo() {
		return dao.getNextNo();
	}
	
	public void save(Board b) {
		dao.save(b);
	}
	
	public Board findById(int no) {
		return dao.findById(no).orElseThrow(()->{
			throw new NoSuchElementException("찾고자 하는 데이터가 없습니다."+no);
		});		
	}
	
	public void updateStep(int b_ref, int b_step) {
		dao.updateStep(b_ref, b_step);
	}
	
	
}
















