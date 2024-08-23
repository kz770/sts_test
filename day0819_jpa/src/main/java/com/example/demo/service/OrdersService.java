package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrdersDAO;
import com.example.demo.vo.OrdersVO;

@Service
public class OrdersService {
	@Autowired
	OrdersDAO dao;
	
	public int getNextOrderId() {
		return dao.getNextOrderId();
	}
	
	public void save(OrdersVO vo) {
		dao.save(vo);
	}
	
	public void insert(OrdersVO vo) {
		dao.insert(vo);
	}
	
	public List<OrdersVO> findAll() {
		return dao.findAllByOrderByOrderid();
	}
	
	public List<OrdersVO> findAll(String keyword){
		if (keyword==null || keyword.equals("")) {
			return dao.findAll();
		}else {
			return dao.findByCustomer_NameContainingOrderByOrderid(keyword);
		}
	}
	
	
}
