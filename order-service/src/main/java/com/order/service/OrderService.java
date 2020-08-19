package com.order.service;

import com.order.exception.OrderNotFound;
import com.order.vo.OrderRequestVO;
import com.order.vo.OrderResponse;

public interface OrderService {

	String addOrder(OrderRequestVO orderVO)throws Exception;

	OrderResponse getOrderDetails(Long orderId)throws OrderNotFound,Exception;

}
