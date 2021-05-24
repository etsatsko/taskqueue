package com.webshop.simplewebapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableWebMvc
public class SimpleWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleWebApplication.class, args);
		System.out.println("http://127.0.0.1:8080/");
	}
}
