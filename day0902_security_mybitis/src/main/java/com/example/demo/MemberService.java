package com.example.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

@Service
public class MemberService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberVO m = DBManager.findByID(username);
		if (m==null) {
			throw new UsernameNotFoundException(username);
			
		}
		
//	UserDetails details=null;
//	UserBuilder builder = User.builder();
//	builder.username(m.getId());
//	builder.password(m.getPwd());
//	builder.roles(m.getRole());
//	details = builder.build();
//	return details;
		System.out.println("load user by user name 동작함");
		System.out.println("회원아이디 : "+m.getId());
		return User.builder()
				.username(m.getId())
				.password(m.getPwd())
				.roles(m.getRole())
				.build();
	}

}
