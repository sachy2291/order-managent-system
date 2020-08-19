package com.order.vo;

import java.util.Set;

import lombok.Data;
@Data
public class OrderRequestVO {

	private Set<Long> productCode;
	
	private OrderVO orderVO;
}
