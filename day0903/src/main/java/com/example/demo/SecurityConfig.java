package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration  //나는 설정파일입니다!
@EnableWebSecurity  //?
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //현재 어플리케이션의 인증과 인가에 대한 내용
        http.authorizeHttpRequests()
                .requestMatchers("/","/join","/all/**").permitAll()
                .requestMatchers("/admin/**").hasRole("admin")
                .anyRequest().authenticated();  //나머지 요청은 인증만 거치면 OK

        http.formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/listBook",true);
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");

        http.httpBasic();
        return http.build();
    }
}
