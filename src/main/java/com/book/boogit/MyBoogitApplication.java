package com.book.boogit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyBoogitApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBoogitApplication.class, args);
	}

}
