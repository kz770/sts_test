package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.GoodsVO;

@Repository
public class GoodsDAO {
	// 클래스 이름의 첫글자만 소문자로 바꿔서 자동으로 객체를 생성해준다.
	// 베이스 패키지의 모든 하위 패키지들을 자동으로 스캔한다.
	
	public List<GoodsVO> findAll(){
		return DBManager.findAll();
	}
	
	public int insert(GoodsVO vo) {
		return DBManager.insert(vo);
	}

	public GoodsVO findByNo(String no) {
		// TODO Auto-generated method stub
		return DBManager.findByNo(no);
	}
	
	public int delete(String no) {
		return DBManager.delete(no);
	}
	public int update(GoodsVO vo) {
		return DBManager.update(vo);
	}
	
	public int totalRecord(HashMap map) {
		return DBManager.totalRecord(map);
	}
	
	public List<GoodsVO> listAll(HashMap map) {
		return DBManager.listAll(map);
	}
}
