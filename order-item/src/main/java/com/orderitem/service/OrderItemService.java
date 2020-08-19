package com.orderitem.service;

import java.util.List;

import com.orderitem.vo.OrderItemVO;

public interface OrderItemService {

	OrderItemVO getOrderItems( Long productIds)throws Exception;

	String addItem( OrderItemVO itemVO)throws Exception;

	List<OrderItemVO> getOrderItemList(List<Long> productCodes)throws Exception;

}
