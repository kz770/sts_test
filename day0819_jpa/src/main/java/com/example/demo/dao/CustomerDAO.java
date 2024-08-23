package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.CustomerVO;

@Repository
public interface CustomerDAO extends JpaRepository<CustomerVO, Integer> {
	
	// 자유롭게 sql을 작성
	@Query(value="select nvl(max(custid),0)+1 from customer", nativeQuery = true)
	public int getNextCustId();	// 인터페이스기 때문에 바디는 적지 않는다

}
