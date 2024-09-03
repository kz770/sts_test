package com.example.demo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		TODO Auto-generated method stub
//		super.configure(auth);
		
		// 사용자의 요청 별 인증과 인가 설정
		//메서드 체이닝 사용가능
		http.authorizeHttpRequests()
		.mvcMatchers("/","/join","/all/**","/error","/header").permitAll()
		.mvcMatchers("/admin/**").hasRole("admin")
		.anyRequest().authenticated();
		
		// 로그인?
		http.formLogin().loginPage("/login").permitAll()
		.defaultSuccessUrl("/listBook");
		
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/login");
		
		http.httpBasic();	// 얜 머지?
	}
}
