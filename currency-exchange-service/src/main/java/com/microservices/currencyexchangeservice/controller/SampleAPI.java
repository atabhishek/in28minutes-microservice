package com.microservices.currencyexchangeservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleAPI {

	@GetMapping("/sample-api")
	@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	public String sampleApi() {

		log.info("Sample Api Recieved");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummyurl",
				String.class);
		return forEntity.getBody();
	}

	@GetMapping("/sample-api-2")
	@CircuitBreaker(name = "sample-api-2", fallbackMethod = "hardcodedResponse")
	public String sampleApiUsingCircuitBreaker() {

		log.info("Sample Api Recieved");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummyurl",
				String.class);
		return forEntity.getBody();
	}
	
	
	@GetMapping("/sample-api-3")
	@RateLimiter(name = "sample-api-3")
	public String sampleApiUsingRateLimiter() {

		log.info("Sample Api Recieved");
		
		return "Smaple Api using Rate Limiter";
	}
	
	
	@GetMapping("/sample-api-4")
	@Bulkhead(name = "sample-api-4")
	public String sampleApiUsingBulkhead() {

		log.info("Sample Api Recieved");
		
		return "Smaple Api using Bulkhead";
	}
	
	public String hardcodedResponse(Exception ex) {
		return "HardCode Response";
	}

}
