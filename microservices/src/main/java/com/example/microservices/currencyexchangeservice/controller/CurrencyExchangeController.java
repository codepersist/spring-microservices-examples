package com.example.microservices.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.example.microservices.currencyexchangeservice.service.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {

	private static final String LOCAL_SERVER_PORT = "local.server.port";

	@Autowired
	private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {
		
		CurrencyExchange currencyExchange = currencyExchangeService.getConcurrencyConversion(from, to);

		if (currencyExchange == null) {
			throw new RuntimeException("Unable to Find data for " + from + " to " + to);
		}
		
		String port = environment.getProperty(LOCAL_SERVER_PORT);
		currencyExchange.setEnvironment(port);
		return currencyExchange;
		
	}
	
}
