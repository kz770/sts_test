package com.example.demo.service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.BookVO;

@Service
public class BookService {
	@Autowired
	private BookDAO dao;

	public List<BookVO> findAll(String sname, String keyword, String searchColumn, String op) {

		// 공백없이 잘 날라오는지 확인하기 위해서 앞뒤로 바를 넣어서 확인해본다
		System.out.println("sname:|" + sname + "|");
		System.out.println("keyword:|" + keyword + "|");
		System.out.println("searchColumn:|" + searchColumn + "|");
		System.out.println("op:|" + op+ "|");

		String methodName = "find";

		if (keyword != null) {
			methodName += "By" + searchColumn;
			
			// op가 null이 아닐 때, ""라면 equals를, 그 외에는 op를 붙여준다
			if (searchColumn.equals("Price")) {
				methodName+=op;
			}

			// 컬럼이 글자이면 뒤에 Containing을 붙인다
			if (searchColumn.equals("Publisher") || searchColumn.equals("Bookname")) {
				methodName += "Containing";
			}

			if (sname != null) {
				methodName += "OrderBy" + sname;
			} 


		} else {
			methodName += "All";
			if (sname != null) {
				methodName += "ByOrderBy" + sname;
			}
		}

		List<BookVO> list = null;
		try {
			Class<?> cls = Class.forName(dao.getClass().getName());
			System.out.println("cls : " + cls);
			Method method = null;

			if (keyword != null) {

				if (searchColumn.equals("Bookid") || searchColumn.equals("Price")) {
					System.out.println("(int)searchColumn : " + searchColumn);
					System.out.println("(int)keyword : " + keyword);
					System.out.println("(int)methodName : " + methodName);
					method = cls.getDeclaredMethod(methodName, Integer.class);
					list = (List<BookVO>) method.invoke(dao, new Integer(keyword));
				} else {
					System.out.println("(String)searchColumn : " + searchColumn);
					System.out.println("(String)keyword : " + keyword);
					System.out.println("(String)methodName : " + methodName);
					method = cls.getDeclaredMethod(methodName, String.class);
					list = (List<BookVO>) method.invoke(dao, keyword);
				}
			} else {
				method = cls.getDeclaredMethod(methodName);
				list = (List<BookVO>) method.invoke(dao);
			}
		} catch (Exception e) {
			System.out.println("예외!!!!!!!" + e.getMessage());
		}
		return list;

	}

	// pk에 해당하는 레코드가 이미 있으면 update를 수행하고
	// 그렇지 않으면 insert를 수행합니다.
	public void save(BookVO b) {
		dao.save(b);
	}

	public BookVO findByBookID(int bookid) {
		return dao.findById(bookid).orElseThrow(()->{
			return new NoSuchElementException("데이터가 없습니다  bookid : "+bookid);
		});
	}
	
	
//	public BookVO findByBookID(int bookid) {
////		return dao.findById(bookid).get();	// 혹시 null 일 수도 있다
//		BookVO b=null;
//		Optional<BookVO> opt= dao.findById(bookid);
//		if (opt.isPresent()) {
//			b=opt.get();
//		}
//		return b;
//	}

	public void delete(int bookid) {
		dao.deleteById(bookid);
	}

}
