package com.microservices.currencyexchangeservice.service;

import org.springframework.stereotype.Service;

import com.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService implements ICurrencyExchangeService {

	private CurrencyExchangeRepository currencyExchangeRepository;

	public CurrencyExchangeService(CurrencyExchangeRepository currencyExchangeRepository) {
		this.currencyExchangeRepository = currencyExchangeRepository;
	}

	@Override
	public CurrencyExchange getCurrerncyExchangeDetails(String from, String to) {

		return currencyExchangeRepository.findByFromAndTo(from, to);
	}

}
