package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.BookVO;

public interface BookDAO extends JpaRepository<BookVO, Integer> {

	//각 컬럼으로 오름차순 정렬하는 메서드 추가
	public List<BookVO> findAllByOrderByBookname();
	public List<BookVO> findAllByOrderByBookid();
	public List<BookVO> findAllByOrderByPrice();
	public List<BookVO> findAllByOrderByPublisher();
	
	// 출판사로 검색하는 메서드
	public List<BookVO> findByPublisherContaining(String publisher);
	
	// 도서명으로 검색하는 메서드
	public List<BookVO> findByBooknameContaining(String Bookname); 
	
	// 가격으로 검색하는 메서드
	public List<BookVO> findByPrice(Integer price);
	
	// 가격으로 검색하는 메서드
	public List<BookVO> findByBookid(Integer Bookid);
	
	//출판사로 검색하여 각 컬럼으로 정렬하는 메서드
	public List<BookVO> findByPublisherContainingOrderByBookname(String publisher);
	public List<BookVO> findByPublisherContainingOrderByBookid(String publisher);
	public List<BookVO> findByPublisherContainingOrderByPrice(String publisher);
	public List<BookVO> findByPublisherContainingOrderByPublisher(String publisher);
	
	//도서명으로 검색하여 각 컬럼으로 정렬하는 메서드
	public List<BookVO> findByBooknameContainingOrderByBookname(String publisher);
	public List<BookVO> findByBooknameContainingOrderByBookid(String publisher);
	public List<BookVO> findByBooknameContainingOrderByPrice(String publisher);
	public List<BookVO> findByBooknameContainingOrderByPublisher(String publisher);
	
	
	
	
	//가격(>)으로 검색하여 각 컬럼으로 정렬하는 메서드
	public List<BookVO> findByPriceGreaterThan(Integer price);
	public List<BookVO> findByPriceGreaterThanOrderByBookname(Integer price);
	public List<BookVO> findByPriceGreaterThanOrderByBookid(Integer price);
	public List<BookVO> findByPriceGreaterThanOrderByPrice(Integer price);
	public List<BookVO> findByPriceGreaterThanOrderByPublisher(Integer price);
	
	//가격(<)으로 검색하여 각 컬럼으로 정렬하는 메서드
	public List<BookVO> findByPriceLessThan(Integer price);
	public List<BookVO> findByPriceLessThanOrderByBookname(Integer price);
	public List<BookVO> findByPriceLessThanOrderByBookid(Integer price);
	public List<BookVO> findByPriceLessThanOrderByPrice(Integer price);
	public List<BookVO> findByPriceLessThanOrderByPublisher(Integer price);
	
	//가격(>=)으로 검색하여 각 컬럼으로 정렬하는 메서드
	public List<BookVO> findByPriceGreaterThanEqual(Integer price);
	public List<BookVO> findByPriceGreaterThanEqualOrderByBookname(Integer price);
	public List<BookVO> findByPriceGreaterThanEqualOrderByBookid(Integer price);
	public List<BookVO> findByPriceGreaterThanEqualOrderByPrice(Integer price);
	public List<BookVO> findByPriceGreaterThanEqualOrderByPublisher(Integer price);
	
	//가격(<=)으로 검색하여 각 컬럼으로 정렬하는 메서드
	public List<BookVO> findByPriceLessThanEqual(Integer price);
	public List<BookVO> findByPriceLessThanEqualOrderByBookname(Integer price);
	public List<BookVO> findByPriceLessThanEqualOrderByBookid(Integer price);
	public List<BookVO> findByPriceLessThanEqualOrderByPrice(Integer price);
	public List<BookVO> findByPriceLessThanEqualOrderByPublisher(Integer price);
	
//	//가격(=)으로 검색하여 각 컬럼으로 정렬하는 메서드
//	public List<BookVO> findByPriceEqualsOrderByBookname(Integer price);
//	public List<BookVO> findByPriceEqualsOrderByBookid(Integer price);
//	public List<BookVO> findByPriceEqualsOrderByPrice(Integer price);
	
	
	//책번호로 검색하여 각 컬럼으로 정렬하는 메서드
	public List<BookVO> findByBookidOrderByBookname(Integer bookid);
	public List<BookVO> findByBookidOrderByBookid(Integer bookid);
	public List<BookVO> findByBookidOrderByPrice(Integer bookid);
	public List<BookVO> findByBookidOrderByPublisher(Integer bookid);
	
	// 
}
