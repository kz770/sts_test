package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Board;

@Repository
public interface boardDAO extends JpaRepository<Board, Integer> {
	
	@Query(value="select * from board order by b_ref desc, b_step", nativeQuery=true)
	public List<com.example.demo.entity.Board> selectAll();
}
