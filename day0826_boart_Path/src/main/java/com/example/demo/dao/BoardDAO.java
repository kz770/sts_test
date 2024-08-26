package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Board;

import jakarta.transaction.Transactional;

@Repository
public interface BoardDAO extends JpaRepository<Board, Integer> {

	@Query(value = "select * from board order by b_ref desc, b_step", nativeQuery = true )
	public List<Board> selectAll();
	
	
	@Query(value = "select nvl(max(no),0) + 1 from board", nativeQuery = true)
	public int getNextNo();
	
	
	@Modifying
	@Query(value = "update Board b set b.b_step=b.b_step+1 where b.b_ref=?1 and b.b_step > ?2", nativeQuery = true)
	@Transactional
	public void updateStep(int b_ref, int b_step);
	
}










