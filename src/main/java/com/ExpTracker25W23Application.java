package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ExpTracker25W23Application {

	public static void main(String[] args) {
		SpringApplication.run(ExpTracker25W23Application.class, args);
	}


	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
}
