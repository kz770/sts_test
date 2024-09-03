package com.example.demo.service;


import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberDAO dao;

    public MemberVO findById(String id){
        return dao.findById(id).get();
    }

    public void save(MemberVO vo){
        dao.save(vo);

    }
}
