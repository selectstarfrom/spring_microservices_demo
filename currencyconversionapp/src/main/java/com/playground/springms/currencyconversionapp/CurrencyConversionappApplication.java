package com.playground.springms.currencyconversionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.playground.springms.currencyconversionapp.proxy")
@EnableDiscoveryClient
public class CurrencyConversionappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionappApplication.class, args);
	}
}
