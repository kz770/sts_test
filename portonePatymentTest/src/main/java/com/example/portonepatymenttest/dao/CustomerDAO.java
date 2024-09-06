package com.example.portonepatymenttest.dao;

import com.example.portonepatymenttest.entity.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, String> {

//    public Customer findById(String id);

}
