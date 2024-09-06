package com.example.day0906_shoppingmall.service;

import com.example.day0906_shoppingmall.dao.MemberDAO;
import com.example.day0906_shoppingmall.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void insert(Member m){
        m.setPwd( passwordEncoder.encode(m.getPwd()) );
        memberDAO.save(m);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> obj = memberDAO.findById(username);
        UserDetails user = null;
        if(obj.isPresent()){
            Member m = obj.get();
            user = User.builder()
                    .username(m.getId())
                    .password(m.getPwd()).roles(m.getRole()).build();
        }else{
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}








