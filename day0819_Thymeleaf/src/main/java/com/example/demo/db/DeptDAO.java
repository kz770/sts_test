package com.example.demo.db;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.DeptVO;

@Repository
public class DeptDAO {
	public List<DeptVO> findAll(){
		return DBManager.findAll();
	}
}
