package com.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.order.exception.OrderNotFound;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler(value = OrderNotFound.class)
	public ResponseEntity<OrderNotFound> handleGenericNotFoundException(OrderNotFound e) {

		OrderNotFound error = new OrderNotFound(e.getErrorcode(), e.getMessage());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
