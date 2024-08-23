package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.BookVO;

import aj.org.objectweb.asm.commons.Method;

@Service
public class BookService2 {

	@Autowired
	private BookDAO dao;

	public List<BookVO> findAll(String sname, String keyword) {
//		if(cname == null) {
//			return dao.findAll();
//		}else if(cname.equals("Bookname")) {
//			return dao.findAllByOrderByBookname();
//		}else if(cname.equals("Bookid")) {
//			return dao.findAllByOrderByBookid();
//		}else if(cname.equals("Publisher")) {
//			return dao.findAllByOrderByPublisher();
//		}else if(cname.equals("Price")) {
//			return dao.findAllByOrderByPrice();
//		}else {
//			return dao.findAll();
//		}

		if (keyword != null) { // 검색어가 있을 때
//			return dao.findByPublisherContaining(keyword);
			if (sname == null || sname.equals("")) {
				return dao.findByPublisherContaining(keyword);
			}else {
				switch (sname) {
				case "Bookname":
					return dao.findByPublisherContainingOrderByBookname(keyword);
				case "Bookid":
					return dao.findByPublisherContainingOrderByBookid(keyword);
				case "Publisher":
					return dao.findByPublisherContainingOrderByPrice(keyword);
				case "Price":
					return dao.findByPublisherContainingOrderByPublisher(keyword);
				default:
					return dao.findAll();
				}
			}
		} else {

			if (sname == null || sname.equals("")) {
				return dao.findAll();
			}
			switch (sname) {
			case "Bookname":
				return dao.findAllByOrderByBookname();
			case "Bookid":
				return dao.findAllByOrderByBookid();
			case "Publisher":
				return dao.findAllByOrderByPrice();
			case "Price":
				return dao.findAllByOrderByPublisher();
			default:
				return dao.findByPublisherContaining(keyword);
			}
		}
		

	}

	public void save(BookVO vo) {
		dao.save(vo);
		// pk에 해당하는 레코드가 이미 있으면 update를 수행
		// 그렇지 않으면 isnert를 수행한다
	}

	public BookVO findByBookID(int bookid) {
		return dao.findById(bookid).get();
	}

	public void delete(int bookid) {
		dao.deleteById(bookid);
	}

//	public List<BookVO> findByPublisher(String publisher){
//		if (publisher==null) {
//			return dao.findAll();
//		}else {
//			return dao.findByPublisher(publisher);
//		}
//	}

}
