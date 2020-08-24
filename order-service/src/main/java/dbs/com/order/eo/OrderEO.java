package dbs.com.order.eo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name="order_deatils")
public class OrderEO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	@NotBlank(message = "Customer Name is mandatory")
	private String customerName;

	private Date  orderDate;
	@NotBlank(message = "Shipping Address is mandatory")
	private String shippingAddress;
	
	private Integer total;
	
	
	


}
