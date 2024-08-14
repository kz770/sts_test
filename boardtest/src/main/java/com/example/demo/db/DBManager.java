package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.VO.BoardVO;

public class DBManager {
	public static SqlSessionFactory factory=null;
	static {
		try {
			String resource = "com/example/demo/db/dbConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<BoardVO> findAll() {
		List<BoardVO> list=null;
		SqlSession session=factory.openSession();
		list=session.selectList("board.findAll");
//		session.commit();	db에 변동이 없는 명령어라서 굳이 커밋이 필요치 않다
		session.close();
		return list;
	}
	
	public static int insert(BoardVO vo) {
		int re=-1;
		SqlSession session=factory.openSession();
		re=session.insert("board.insert",vo);
		session.commit();	//auto 커밋을 하면 여러 명령 중 하나만 완료될 수 있다.
		// 따라서 모두 정상적으로 완료가 되었을 때 commit 명령을 실행하도록 한다.
		session.close();
		return re;
	}
	public static int update(BoardVO vo) {
		int re=-1;
		SqlSession session=factory.openSession();
		re=session.update("board.updateById",vo);
		session.commit();	//auto 커밋을 하면 여러 명령 중 하나만 완료될 수 있다.
		// 따라서 모두 정상적으로 완료가 되었을 때 commit 명령을 실행하도록 한다.
		session.close();
		return re;
	}
	
	public static BoardVO findByNo(int no) {
		BoardVO vo=null;
		SqlSession session = factory.openSession()	;
		vo=session.selectOne("board.findByNo",no);
		session.close();
		return vo;
	}
	
	public static int deleteByNo(int no, String pwd) {
		int re=-1;
		SqlSession session=factory.openSession();
		HashMap map=new HashMap<>();
		map.put("no", no);
		map.put("pwd", pwd);
		re=session.delete("board.delete",map);
		session.commit();
		session.close();
		return re;
	}
}
