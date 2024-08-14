package com.example.demo.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.VO.BookVO;
import com.example.demo.db.DBManager;

@Repository
public class BookDAO {
	public List<BookVO> findAll() {
		return DBManager.findAll();
	}
}
