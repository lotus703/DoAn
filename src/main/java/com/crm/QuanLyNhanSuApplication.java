package com.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.crm")

@EntityScan(basePackages = "com.crm")
public class QuanLyNhanSuApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanLyNhanSuApplication.class, args);
	}

}
