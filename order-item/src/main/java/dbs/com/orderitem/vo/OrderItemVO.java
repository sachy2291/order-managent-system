package dbs.com.orderitem.vo;

import lombok.Data;

@Data
public class OrderItemVO {
	
	private Long productCode;

	private String productName;

	private int quantity;

}
