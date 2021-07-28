package com.microservices.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.microservices.currencyexchangeservice.service.ICurrencyExchangeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/currency-exchange/from")
public class CurrencyExchangeController {

	private Environment environmet;
	private ICurrencyExchangeService currencyExchangeServiceImpl;

	@Autowired
	public CurrencyExchangeController(Environment environmet, ICurrencyExchangeService currencyExchangeServiceImpl) {
		this.environmet = environmet;
		this.currencyExchangeServiceImpl = currencyExchangeServiceImpl;
	}

	@GetMapping("{from}/to/{to}")
	public CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
//		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50), null);
		log.info("currency exchange request {} {}",from,to);
		CurrencyExchange currencyExchange = currencyExchangeServiceImpl.getCurrerncyExchangeDetails(from, to);

		if (null == currencyExchange)
			throw new RuntimeException("unable to find the details for form " + from + " and to " + to);
		currencyExchange.setEnvironment(environmet.getProperty("local.server.port"));

		return currencyExchange;
	}

}
