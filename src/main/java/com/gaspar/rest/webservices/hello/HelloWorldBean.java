package com.gaspar.rest.webservices.hello;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean() {
		super();
	}

	public HelloWorldBean(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

}
