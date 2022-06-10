package com.example.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.microservices.currencyconversionservice.CurrencyExchangeProxy;
import com.example.microservices.currencyconversionservice.bean.CurrencyConversion;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;

	//Calling other micro-service with RestTemplate
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);

		CurrencyConversion currencyConversion = responseEntity.getBody();

		return new CurrencyConversion(currencyConversion.getId(), 
				from, to, quantity, 
				currencyConversion.getConversionMultiple(), 
				quantity.multiply(currencyConversion.getConversionMultiple()), 
				currencyConversion.getEnvironment());

	}

		//Calling other micro service with Feign client
		@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
		public CurrencyConversion calculateCurrencyConversionFeign(
				@PathVariable String from, 
				@PathVariable String to,
				@PathVariable BigDecimal quantity) {

			CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

			return new CurrencyConversion(currencyConversion.getId(), 
					from, to, quantity, 
					currencyConversion.getConversionMultiple(), 
					quantity.multiply(currencyConversion.getConversionMultiple()), 
					currencyConversion.getEnvironment() + " " + "retured from feign client");
		}

}
