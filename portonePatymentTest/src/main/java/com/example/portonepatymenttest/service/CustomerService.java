package com.example.portonepatymenttest.service;

import com.example.portonepatymenttest.dao.CustomerDAO;
import com.example.portonepatymenttest.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    public CustomerDAO dao;

    public Customer findCustomerById(String  username) {
        return dao.findById(username).get();
    }

    public Customer save(Customer customer) {
        return dao.save(customer);
    }

    // Spring Security의 post기능 작동 시 필요한 userdetail을 반환해주는 메서드
    // 여기에서 user의 id, pwd, role을 지정해준다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer=findCustomerById(username);
        if (customer==null){
            throw new UsernameNotFoundException(username);
        }
//        System.out.println("user detail 동작");


        return User.builder()
                .username(customer.getId())
                .password(customer.getPwd())
                .roles(customer.getRole())
                .build();
    }
}
