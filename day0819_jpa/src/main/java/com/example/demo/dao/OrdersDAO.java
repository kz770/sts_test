package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.OrdersVO;

import jakarta.transaction.Transactional;

@Repository
public interface OrdersDAO extends JpaRepository<OrdersVO, Integer> {
	// 자유롭게 sql을 작성
	@Query(value = "select nvl(max(orderid),0)+1 from orders", nativeQuery = true)
	public int getNextOrderId(); // 인터페이스기 때문에 바디는 적지 않는다
	
	@Modifying
	@Query(value="insert into orders o (o.orderid, o.custid, o.bookid, o.saleprice, o.orderdate)"
			+ " values(:#{#vo.orderid}, :#{#vo.customer.custid}, :#{#vo.book.bookid}, :#{#vo.saleprice}, sysdate)", nativeQuery = true)
	@Transactional
	public void insert(@Param("vo") OrdersVO vo);
	
	public List<OrdersVO> findByOrderdate(Date orderdate);
	
	public List<OrdersVO> findAllByOrderByOrderid();
	public List<OrdersVO> findByCustomer_NameContainingOrderByOrderid(String name);	// 이거 소문자로 하며 안되나여?
}
