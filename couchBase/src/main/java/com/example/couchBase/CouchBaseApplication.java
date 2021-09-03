package com.example.couchBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouchBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouchBaseApplication.class, args);
	}

}
