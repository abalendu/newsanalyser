package com.noida.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
   @Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean responseBody = proxy.retriveExchangeValue(from, to);
		
		return new CurrencyConversionBean(responseBody.getId(), from, to, responseBody.getConversionMultiple(), quantity,
				quantity.multiply(responseBody.getConversionMultiple()), responseBody.getPort());

	}
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		Map<String, String> uriVariable = new HashMap<>();
		uriVariable.put("from", from);
		uriVariable.put("to", to);

		ResponseEntity<CurrencyConversionBean> response = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariable);
		CurrencyConversionBean responseBody = response.getBody();
		return new CurrencyConversionBean(responseBody.getId(), from, to, responseBody.getConversionMultiple(), quantity,
				quantity.multiply(responseBody.getConversionMultiple()), responseBody.getPort());

	}
}
