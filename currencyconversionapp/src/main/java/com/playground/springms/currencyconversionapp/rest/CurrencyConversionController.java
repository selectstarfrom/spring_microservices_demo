package com.playground.springms.currencyconversionapp.rest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.playground.springms.currencyconversionapp.beans.CurrencyConversionBean;
import com.playground.springms.currencyconversionapp.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	private static final String URL_CURRENCY_X = "http://localhost:8000/forexapp/currency-exchange/from/{from}/to/{to}";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CurrencyConversionBean> responseEntity = null;
		responseEntity = restTemplate.getForEntity(URL_CURRENCY_X, CurrencyConversionBean.class, uriVariables);

		CurrencyConversionBean response = responseEntity.getBody();

		Long id = response.getId();
		BigDecimal conversionMultiple = response.getConversionMultiple();
		BigDecimal multiply = quantity.multiply(conversionMultiple);
		int port = response.getPort();

		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean(id, from, to, conversionMultiple,
				quantity, multiply, port);

		return currencyConversionBean;
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		logger.info("{}", response);

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

}