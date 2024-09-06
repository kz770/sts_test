package com.example.portonepatymenttest.dao;

import com.example.portonepatymenttest.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDAO extends JpaRepository<Payment, Integer> {

}
