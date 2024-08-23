package com.example.demo.vo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class OrdersVO {

	@Id
	private int orderid;
	
	//custid를 직접 선언하는게 아니라 연관관계에 있는 entity를 가져온다
	@ManyToOne // 나 : N - 상대방(부모) : 1
	@JoinColumn(name="custid", insertable=true, updatable=true)
	private CustomerVO customer;
	
	@ManyToOne
	@JoinColumn(name="bookid", insertable=true, updatable=true)
	private BookVO book;
	
	private Date orderdate;
	
	private int saleprice;
}
