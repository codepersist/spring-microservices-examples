package com.example.microservices.currencyexchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.example.microservices.currencyexchangeservice.bean")
@EnableJpaRepositories("com.example.microservices.currencyexchangeservice.dao")
@ComponentScan({"com.example.microservices.currencyexchangeservice.controller","com.example.microservices.currencyexchangeservice.service"})
@EnableAutoConfiguration
@SpringBootConfiguration
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}

}
