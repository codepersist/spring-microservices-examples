package com.example.microservices.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.limitservice.beans.Limits;
import com.example.microservices.limitservice.configuration.LimitConfiguration;

@RestController
public class LimitsController {

	@Autowired
	private LimitConfiguration limitConfiguration;
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(limitConfiguration.getMinimum(),limitConfiguration.getMaximum());
	}
}
