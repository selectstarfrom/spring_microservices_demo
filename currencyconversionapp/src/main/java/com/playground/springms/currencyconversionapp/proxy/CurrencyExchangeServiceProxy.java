package com.playground.springms.currencyconversionapp.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.playground.springms.currencyconversionapp.beans.CurrencyConversionBean;

@FeignClient(name = "forex-service", url = "localhost:8000/forexapp")
public interface CurrencyExchangeServiceProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);

}