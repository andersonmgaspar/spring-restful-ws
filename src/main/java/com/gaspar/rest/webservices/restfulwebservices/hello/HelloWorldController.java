package com.gaspar.rest.webservices.restfulwebservices.hello;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	MessageSource messageSource;

	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@GetMapping(path = "/hello")
	public String sayHello() {
		return "Hi there!";
	}

	@GetMapping(path = "/hello-bean")
	public HelloWorldBean sayHelloBean() {
		return new HelloWorldBean("Hi there!");
	}

	@GetMapping(path = "/hello-bean/{name}")
	public HelloWorldBean sayHelloBeanPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hi %s, how are you today? :)", name));
	}

	@GetMapping(path = "/hello-i18n")
	public String sayHelloI18n() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Andi Systems LTDA.", locale);
	}

}
