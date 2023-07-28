package com.cassandra.rampup;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RampupApplication {

	public static void main(String[] args) {
		SpringApplication.run(RampupApplication.class, args);
	}

	@PostConstruct
	public void start(){

		System.out.println("Application started");
	}

}
