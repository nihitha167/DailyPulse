package com.nihitha.dailypulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Client (Postman/UI) → Controller(interaction with outside world like http requests) → Service(business logic and add rules for application) → Repository(data access layer - talk to data , query) → DB (data layer)

@SpringBootApplication
public class DailypulseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailypulseApplication.class, args);
	}

}
