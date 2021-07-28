package com.in28Minutes.microservices.limitmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28Minutes.microservices.limitmicroservice.configuration.LimitConfiguration;
import com.in28Minutes.microservices.limitmicroservice.model.Limit;

@RestController
public class LimitController {

	private LimitConfiguration limitConfiguration;

	@Autowired
	public LimitController(LimitConfiguration limitConfiguration) {
		this.limitConfiguration = limitConfiguration;
	}

	@GetMapping("/limits")
	public Limit retrieveAllLimit() {

		return new Limit(limitConfiguration.getMinimum(), limitConfiguration.getMaximum());
	}

}
