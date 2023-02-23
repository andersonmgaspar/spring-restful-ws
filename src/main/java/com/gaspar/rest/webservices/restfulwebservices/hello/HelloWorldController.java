package com.gaspar.rest.webservices.restfulwebservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path = "/hello")
	public String sayHello() {
		return "Hi there!";
	}
	
	@GetMapping(path = "/hello-bean")
	public HelloWorldBean sayHelloBean() {
		return new HelloWorldBean("Hi there!");
	}
	
}
