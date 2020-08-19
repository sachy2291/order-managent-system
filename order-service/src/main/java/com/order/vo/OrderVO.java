package com.order.vo;

import java.util.Date;

import lombok.Data;

@Data
public class OrderVO {
	private Long orderId;
	
	private String customerName;

	private Date  orderDate;
	
	private String shippingAddress;
	
	private Integer total;
	


}
