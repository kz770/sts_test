package com.example.demo;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day0823OptionalApplication {
	public static void pro(Person p) {
		System.out.println(p.getAge());
	}
	public static void pro2(Optional<Person> p) {
		System.out.println(p.orElse(new Person()).getAge());
	}

	public static void main(String[] args) {
		SpringApplication.run(Day0823OptionalApplication.class, args);
//		Person kim=null;
//		pro(kim);
		
		Person kim = null;
		
		pro2(Optional.ofNullable(kim));	//null 일 수 있어
//		pro2(Optional.of(kim));	// 확실히 null이 아니야!
	}
}
