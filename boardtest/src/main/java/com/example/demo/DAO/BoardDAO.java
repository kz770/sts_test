package com.example.demo.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.VO.BoardVO;
import com.example.demo.db.DBManager;

@Repository
public class BoardDAO {
	public List<BoardVO> findAll(){
		return DBManager.findAll();
	}
	
	public int insert(BoardVO vo) {
		return DBManager.insert(vo);
	}
	
	public BoardVO findByNo(int no) {
		return DBManager.findByNo(no);
	}
	
	public int delete(int no, String pwd) {
		return DBManager.deleteByNo(no, pwd);
	}
	public int update(BoardVO vo) {
		return DBManager.update(vo);
	}
}
