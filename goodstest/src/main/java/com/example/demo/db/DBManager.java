package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.GoodsVO;

public class DBManager {
	public static SqlSessionFactory factory=null;
	
	// 프로그램 시작과 동시에 자동으로 수행시키기 위하여 static 블럭에 작성한다.
	static {
		// 파일 처리를 위해 예외처리
		try {
			String resource = "com/example/demo/db/SqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch (Exception e) {
			System.out.println("DBManager 예외 발생 ==> "+e.getMessage());
		}
	}
	
	public static List<GoodsVO> findAll(){
		List<GoodsVO> list = null;
		// mybatis 설정파일의 sql을 요청하기 위해서
		// sqlsession을 얻어온다
		SqlSession session = factory.openSession();	// 위에서 try-catch 처리 한 factory의 session
		
		//sql session을 통해서 sql을 요청한다
		//mapper에 설정한 이름으로 메서드 가져오기
		list = session.selectList("goods.findAll");
		
		session.close();
				
		return list;
	}
	
	public static int insert(GoodsVO vo) {
		int re=-1;
		SqlSession session=factory.openSession();
		re=session.insert("goods.insert",vo);
		session.commit();
		session.close();
		return re;
	}
	
	public static GoodsVO findByNo(String no){
		GoodsVO vo;
		SqlSession session=factory.openSession();
		vo=session.selectOne("goods.findByNo", no);
		return vo;
	}
	
	public static int delete(String no) {
		int re=-1;
		SqlSession session=factory.openSession();
		re=session.delete("goods.delete",no);
		session.commit();
		session.close();
		return re;
	}
	
	public static int update(GoodsVO vo) {
		int re=-1;
		SqlSession session=factory.openSession();
		re=session.update("goods.update",vo);
		session.commit();
		session.close();
		return re;
	}
	
	public static int totalRecord(HashMap map) {
		int cnt=0;
		SqlSession session=factory.openSession();
		cnt=session.selectOne("goods.totalRecord",map);
		return cnt;
	}
	
	public static List<GoodsVO> listAll(HashMap map){
		List<GoodsVO> list=null;
		SqlSession session=factory.openSession();
		list=session.selectList("goods.listAll", map);
		return list;
	}
	
}
