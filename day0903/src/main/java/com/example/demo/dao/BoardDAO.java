package com.example.demo.dao;

import com.example.demo.vo.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardDAO extends JpaRepository<Board, Integer> {

    @Query(value="select (nvl(max(no),0)+1) from board", nativeQuery = true)
    public int getNextNo();

    @Query(value="select no, title, writer, pwd, content, regdate, hit, fname, fsize, ip, b_ref, b_level, b_step from ("
            + "    select rownum r, b.* from ("
            + "        select * from board"
            + "        order by b_ref desc, b_step asc"
            + "    ) b"
            + ") where r >= ?1 and r <= ?2", nativeQuery=true)
    public List<Board> selectAll(int start, int end);

    @Query(value="select no, title, writer, pwd, content, regdate, hit, fname, fsize, ip, b_ref, b_level, b_step from ("
            + "    select rownum r, b.* from ("
            + "        select * from board where writer=?3"
            + "        order by b_ref desc, b_step asc"
            + "    ) b"
            + ") where r >= ?1 and r <= ?2", nativeQuery=true)
    public List<Board> findAll(int start, int end,String id);

    @Query(value="select * from board where writer=?1", nativeQuery = true)
    public List<Board> selectByWriter(String writer);

    @Query(value="select count(*) from board where writer=?1",nativeQuery = true)
    public int getCount(String id);
}
