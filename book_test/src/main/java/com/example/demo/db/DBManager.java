package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import javax.security.sasl.SaslServerFactory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.VO.BookVO;

public class DBManager {
	public static SqlSessionFactory factory=null;
	static {
		try {
			String resource = "org/example/demo/db/dbConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<BookVO> findAll() {
		List<BookVO> list=null;
		SqlSession session=factory.openSession();
		list=session.selectList("book.findAll");
//		session.commit();	db에 변동이 없는 명령어라서 굳이 커밋이 필요치 않다
		session.close();
		return list;
	}
}
