package com.raj.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.raj")
public class TestTransactionLoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestTransactionLoggingApplication.class, args);
	}
}
