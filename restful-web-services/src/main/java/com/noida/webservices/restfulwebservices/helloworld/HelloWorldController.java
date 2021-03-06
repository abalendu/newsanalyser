package com.noida.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello world";
	}
	
	@GetMapping(path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name ) {
		return new HelloWorldBean(String.format("Hello world %s", name));
	}
	
	@GetMapping(path="/hello-world-i18n")
	public String helloWorldI18n(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message",null, locale);
	}
}

