package dbs.com.order.eo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="orderitem_order_mapping")
public class OrderItemOrderMappingEO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mappingId;
	
	private Long orderId;
	
	private Long productCode;

}
