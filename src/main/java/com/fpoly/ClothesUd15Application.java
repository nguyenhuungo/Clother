package com.fpoly;

import com.fpoly.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ClothesUd15Application {

	public static void main(String[] args) {
		SpringApplication.run(ClothesUd15Application.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args ->{
			storageService.init();
		});
	}
}
