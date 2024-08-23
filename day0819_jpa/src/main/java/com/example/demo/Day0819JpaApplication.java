package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day0819JpaApplication {

	public static void main(String[] args) {

		String data = "findByPriceContainingOrderByPrice";
		data = data.replace("Containing", "");
		System.out.println(data);

		SpringApplication.run(Day0819JpaApplication.class, args);
	}

}
