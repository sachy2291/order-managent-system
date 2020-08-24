package dbs.com.order.service;

import dbs.com.order.exception.OrderNotFound;
import dbs.com.order.vo.OrderRequestVO;
import dbs.com.order.vo.OrderResponse;

public interface OrderService {

	String addOrder(OrderRequestVO orderVO);

	OrderResponse getOrderDetails(Long orderId)throws OrderNotFound,Exception;

}
