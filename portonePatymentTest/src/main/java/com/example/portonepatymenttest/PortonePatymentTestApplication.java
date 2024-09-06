package com.example.portonepatymenttest;

import com.example.portonepatymenttest.dao.CustomerDAO;
import com.example.portonepatymenttest.entity.Customer;
import com.example.portonepatymenttest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PortonePatymentTestApplication {

//    @Autowired
//    private CustomerService customerService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(PortonePatymentTestApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo() {
//        return args -> {
//            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//            Customer spring = new Customer();
//            spring.setName("spring");
//            spring.setId("spring");
//            spring.setPwd(passwordEncoder.encode("spring"));
//            spring.setPhone("010-1234-5678");
//            spring.setEmail("spring@gmail.com");
//            spring.setAddress("Jongro-Seoul");
//            spring.setRole("admin");
//
//            Customer summer = new Customer();
//            summer.setName("summer");
//            summer.setId("summer");
//            summer.setPwd(passwordEncoder.encode("summer"));
//            summer.setPhone("010-1234-7888");
//            summer.setEmail("summer@gmail.com");
//            summer.setAddress("Incheon");
//            summer.setRole("user");
//
//            customerService.save(spring);
//            customerService.save(summer);
//        };
//    }
}
