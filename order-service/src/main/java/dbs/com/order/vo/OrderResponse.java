package dbs.com.order.vo;

import java.util.Set;

import lombok.Data;

@Data
public class OrderResponse {

	private OrderVO orderVO;

	private Set<OrderItemVO> itemVO;
}
