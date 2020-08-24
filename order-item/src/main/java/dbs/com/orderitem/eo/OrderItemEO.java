package dbs.com.orderitem.eo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name="order_item")
public class OrderItemEO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productCode;
	@NotBlank(message = "Product Name is mandatory")
	private String productName;
	
	private int quantity;

}
