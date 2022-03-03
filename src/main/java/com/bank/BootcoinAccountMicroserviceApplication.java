package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@SpringBootApplication
public class BootcoinAccountMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcoinAccountMicroserviceApplication.class, args);
	}
	@Bean
	public RecordMessageConverter converter() {
		return new JsonMessageConverter();
	}
}
