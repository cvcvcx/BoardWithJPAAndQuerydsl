package com.boardwithJPA.cvcvcx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CvcvcxApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvcvcxApplication.class, args);
	}

}
