package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.BoardVO;

@Repository
public class BoardDAO {
	
	public void updateStep(HashMap map) {
		System.out.println("update step이 동작함");
		DBManager.updateStep(map);
	} 
	
	public int getNextNo() {
		System.out.println("getNextNo가 동작함");
		return DBManager.getNextNo();
	}
	public List<BoardVO> findAll(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("findAll이 동작함.");
		return DBManager.findAll();
	}
	
	public int insert(BoardVO b) {
		System.out.println("insert가 동작함");
		return DBManager.insert(b);
	}
	
	public BoardVO findByNo(int no) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("findByNo가 동작함.");
		return DBManager.findByNo(no);
	}
	
	public int update(BoardVO b) {
		System.out.println("update가 동작함");
		return DBManager.update(b);
	}
	
	public int delete(int no, String pwd) {
		System.out.println("delete가 동작함");
		System.out.println(4/0);
		return DBManager.delete(no, pwd);
	}
	
}













