package com.microservices.currencyexchangeservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservices.currencyexchangeservice.model.CurrencyExchange;

public interface CurrencyExchangeRepository extends CrudRepository<CurrencyExchange, Long> {
	
	public CurrencyExchange findByFromAndTo(String from,String to);

}
