package com.appswaves;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AppsWaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppsWaveApplication.class, args);
	}
}
