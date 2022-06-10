package com.example.microservices.currencyexchangeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.example.microservices.currencyexchangeservice.dao.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService{

	@Autowired
	CurrencyExchangeRepository currencyExchangeRepository;

	@Override
	public CurrencyExchange getConcurrencyConversion(String from, String to) {
		return currencyExchangeRepository.findByFromAndTo(from, to);
	}

}
