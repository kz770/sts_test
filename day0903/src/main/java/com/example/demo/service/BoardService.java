package com.example.demo.service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardService {
    @Autowired
    BoardDAO dao;

    public int getCount(){
        return (int)dao.count();
    }

    public List<Board> getBoards(int start, int end) {
        return dao.selectAll(start, end);
    }

    public List<Board> getListByWriter(String writer) {
        return dao.selectByWriter(writer);
    }

    public List<Board> findAll(int start, int end,String writer) {
        return dao.findAll(start,end,writer);
    }
    public List<Board> findAll(int start, int end) {
        return dao.selectAll(start, end);
    }

    public int getCount(String id){
        return dao.getCount(id);
    }


}
