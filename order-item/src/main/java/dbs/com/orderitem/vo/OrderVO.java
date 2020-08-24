package dbs.com.orderitem.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderVO {

	private Long orderId;

	private String customerName;

	private LocalDate orderDate;

	private String shippingAddress;

	private Integer total;
}
