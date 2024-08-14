package com.example.demo.db;

import java.awt.print.Book;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardVO;
import com.example.demo.vo.EmpVO;
import com.example.demo.vo.MemberVO;



public class DBManager {
	public static SqlSessionFactory factory = null;
	static {
		try {
			String resource = "com/example/demo/db/dbConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static List<EmpVO> listEmp(){
		List<EmpVO>  list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("emp.findAll");
		session.close();
		return list;
	}	
	
	
	public static int join(MemberVO m) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.insert("member.insert", m);
		session.close();
		return re;
	}
	
	public static List<BoardVO> findAll(){
		List<BoardVO>  list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("board.findAll");
		session.close();
		return list;
	}	
	
	public static int insert(BoardVO b) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.insert("board.insert", b);
//		session.commit();
		session.close();
		return re;
	}
	
	public static int update(BoardVO b) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.update("board.update", b);
//		session.commit();
		session.close();
		return re;
	}
	
	public static BoardVO findByNo(int no) {
		BoardVO b = null;
		SqlSession session = factory.openSession();
		b = session.selectOne("board.findByNo", no);
		session.close();
		return b;
	}
	
	public static int delete(int no, String pwd) {
		int re = -1;
		HashMap map = new HashMap();
		map.put("no",no);
		map.put("pwd",pwd);
		
		SqlSession session  = factory.openSession(true);
		re = session.delete("board.delete", map);
		session.close();
		
		return re;
	}
	
	public static int getNextNo() {
		int no = 0;
		SqlSession session = factory.openSession();
		no = session.selectOne("board.nextNo");
		session.close();
		return no;
	}
	
	public static void updateStep(HashMap map) {
		SqlSession session = factory.openSession();
		session.update("board.updateStep", map);
		session.commit();
		session.close();
	}
}






















