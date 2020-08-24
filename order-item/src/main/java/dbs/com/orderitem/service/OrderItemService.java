package dbs.com.orderitem.service;

import java.util.List;

import dbs.com.orderitem.exception.ProductCodeNotFoundException;
import dbs.com.orderitem.vo.OrderItemVO;

public interface OrderItemService {

	OrderItemVO getOrderItems( Long productIds)throws ProductCodeNotFoundException;

	String addItem( OrderItemVO itemVO);

	List<OrderItemVO> getOrderItemList(List<Long> productCodes)throws ProductCodeNotFoundException;

}
