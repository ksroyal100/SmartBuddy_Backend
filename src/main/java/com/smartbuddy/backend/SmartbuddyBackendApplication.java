package com.smartbuddy.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartbuddyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartbuddyBackendApplication.class, args);
		System.out.println("Hello Backend Server");
	}

}
