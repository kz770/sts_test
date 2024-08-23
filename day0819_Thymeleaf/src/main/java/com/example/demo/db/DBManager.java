package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.DeptVO;

public class DBManager {
	public static SqlSessionFactory factory;
	
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("sql session factory error : "+e.getMessage());
		}
	}
	
	public static List<DeptVO> findAll(){
		List<DeptVO> list = null;
		SqlSession session=factory.openSession();
		list = session.selectList("dept.findAll");
		session.close();
		
		return list;
	}
}
