package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.vo.CustomerVO;

@Service
public class CustomerService {
	@Autowired
	public CustomerDAO dao;
	
	public List<CustomerVO> findAll(){
		return dao.findAll(Sort.by("custid"));
	}
	
	public CustomerVO findById(int custid) {
		return dao.findById(custid).get();
	}
	
	public void save(CustomerVO vo) {
		dao.save(vo);
	}

	public int getNextCustid() {
		return dao.getNextCustId();
	}
	
	public void deleteById(int custid) {
		dao.deleteById(custid);
	}
}
