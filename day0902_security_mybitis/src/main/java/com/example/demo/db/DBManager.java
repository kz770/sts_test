package com.example.demo.db;


import java.io.InputStream;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.MemberVO;

@Repository
public class DBManager {	
	public static SqlSessionFactory sqlSessionFactory= null;	
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
//			Reader reader = Resources.getResourceAsReader(resource);
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static MemberVO findByID(String id) {
		MemberVO m = null;
		SqlSession session=sqlSessionFactory.openSession();
		m=session.selectOne("member.findByID",id);
		session.close();
		return m;
	}
	
	public static int insert(MemberVO m) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("member.insert", m);
		session.commit();
		session.close();
		return re;
	}
}

