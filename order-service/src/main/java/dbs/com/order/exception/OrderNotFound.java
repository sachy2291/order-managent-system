package dbs.com.order.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class OrderNotFound extends Throwable{
	
	private static final long serialVersionUID = 1L;
	
	private Date timestamp;

	private String description;
	
	private String message;

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public OrderNotFound(String message) {
		super();
		this.message = message;
	}

	public OrderNotFound(Date timestamp, String description, String message) {
		super();
		this.timestamp = timestamp;
		this.description = description;
		this.message = message;
	}

	@Override
	public String toString() {
		return "OrderNotFound [timestamp=" + timestamp + ", description=" + description + ", message=" + message + "]";
	}

	
	

}
