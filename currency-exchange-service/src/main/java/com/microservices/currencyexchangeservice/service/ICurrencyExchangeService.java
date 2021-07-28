package com.microservices.currencyexchangeservice.service;

import com.microservices.currencyexchangeservice.model.CurrencyExchange;

public interface ICurrencyExchangeService{
	
	public CurrencyExchange getCurrerncyExchangeDetails(String from,String to);

}
