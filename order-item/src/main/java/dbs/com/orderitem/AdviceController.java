package dbs.com.orderitem;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import dbs.com.orderitem.exception.ProductCodeNotFound;
import dbs.com.orderitem.exception.ProductCodeNotFoundException;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler(value = ProductCodeNotFoundException.class)
	public ResponseEntity<ProductCodeNotFound> handleGenericNotFoundException(ProductCodeNotFound e,
			WebRequest request) {
		ProductCodeNotFound error = new ProductCodeNotFound(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExcpetionHandler(Exception e, WebRequest request) {
		ProductCodeNotFound errorDetails = new ProductCodeNotFound(new Date(), e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
