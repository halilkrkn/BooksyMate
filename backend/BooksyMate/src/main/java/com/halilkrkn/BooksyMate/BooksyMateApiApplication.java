package com.halilkrkn.BooksyMate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BooksyMateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksyMateApiApplication.class, args);
	}

}
