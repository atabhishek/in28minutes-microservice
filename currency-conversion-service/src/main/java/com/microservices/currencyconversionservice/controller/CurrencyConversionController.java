package com.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.currencyconversionservice.model.CurrencyConversion;
import com.microservices.currencyconversionservice.service.CurrencyExchangeProxy;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

	private final static String CURRENCY_EXCHANGE_URL = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";

	private CurrencyExchangeProxy feignClient;

	@Autowired
	public CurrencyConversionController(CurrencyExchangeProxy feignClient) {

		this.feignClient = feignClient;
	}

	@GetMapping("/from/{from}/to/{to}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@RequestParam BigDecimal quantity) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		Map<String, String> requestParams = new HashMap<>();
		requestParams.put("from", from);
		requestParams.put("to", to);

		HttpEntity<Map<String, String>> entity = new HttpEntity<>(null, headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<CurrencyConversion> response = restTemplate.exchange(CURRENCY_EXCHANGE_URL, HttpMethod.GET,
				entity, CurrencyConversion.class, requestParams);

		CurrencyConversion currencyConversion = response.getBody();

		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment()+" "+"restTemplate");
	}

	@GetMapping("/feign/from/{from}/to/{to}")
	public CurrencyConversion calculateCurrencyConversionUsingFeign(@PathVariable String from, @PathVariable String to,
			@RequestParam BigDecimal quantity) {

		CurrencyConversion currencyConversion = feignClient.getCurrencyExchange(from, to);

		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment()+" "+"feign");
	}

}
