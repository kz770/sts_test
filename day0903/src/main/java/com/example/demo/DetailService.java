package com.example.demo;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
public class DetailService implements UserDetailsService {

    @Autowired
    public MemberDAO dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO m = dao.findById(username).get();
        if (m==null){
            throw new UsernameNotFoundException(username);
        }

        System.out.println("user details 동작");
        System.out.println("회원 아이디 : "+m.getId());

        return User.builder()
                .username(m.getId())
                .password(m.getPwd())
                .roles(m.getRole())
                .build();
    }
}
