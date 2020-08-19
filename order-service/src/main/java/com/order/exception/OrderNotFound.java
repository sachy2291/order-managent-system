package com.order.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFound extends Throwable{
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus errorcode;

	private String description;
	

	public HttpStatus getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(HttpStatus errorcode) {
		this.errorcode = errorcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public OrderNotFound(HttpStatus errorcode, String description) {
		super();
		this.errorcode = errorcode;
		this.description = description;
	}

	
	@Override
	public String toString() {
		return "OrderNotFound [errorcode=" + errorcode + ", description=" + description + "]";
	}

	
	

}
