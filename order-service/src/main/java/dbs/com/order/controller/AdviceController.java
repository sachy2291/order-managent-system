package dbs.com.order.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import dbs.com.order.exception.OrderNotFound;
import dbs.com.order.exception.OrderNotFoundException;
import dbs.com.order.exception.ProductCodeNotFoundException;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler(value = OrderNotFoundException.class)
	public ResponseEntity<OrderNotFound> handleGenericOrderNotFoundException(OrderNotFound e, WebRequest request) {

		OrderNotFound error = new OrderNotFound(new Date(), e.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ProductCodeNotFoundException.class)
	public ResponseEntity<OrderNotFound> handleGenericProductNotFoundException(OrderNotFound e, WebRequest request) {

		OrderNotFound error = new OrderNotFound(new Date(), e.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception e, WebRequest request) {
		OrderNotFound errorDetails = new OrderNotFound(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
