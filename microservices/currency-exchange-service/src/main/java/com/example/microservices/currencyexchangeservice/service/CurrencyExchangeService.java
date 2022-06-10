package com.example.microservices.currencyexchangeservice.service;

import com.example.microservices.currencyexchangeservice.bean.CurrencyExchange;

public interface CurrencyExchangeService {

	CurrencyExchange getConcurrencyConversion(String from, String to);
}
