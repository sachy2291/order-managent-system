package com.order.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.order.eo.OrderEO;
import com.order.eo.OrderItemOrderMappingEO;
import com.order.exception.OrderNotFound;
import com.order.feignclient.OrderItemFeignClient;
import com.order.mapper.OrderMapper;
import com.order.repository.OrderItemOrderMappingRepo;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import com.order.vo.OrderItemVO;
import com.order.vo.OrderRequestVO;
import com.order.vo.OrderResponse;
import com.order.vo.OrderVO;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemFeignClient orderItemFeignClient;
	
	@Autowired
	private OrderItemOrderMappingRepo orderItemOrderMappingRepo;

	@Override
	public String addOrder(OrderRequestVO orderRequestVO) throws Exception {
		String msg=null;
		try {
			OrderVO orderVO=new OrderVO();
			orderVO=orderRequestVO.getOrderVO();
			OrderEO orderEO=OrderMapper.INSTANCE.fromOrderVOToOrderEO(orderVO);
			OrderEO upOrderEO=orderRepository.save(orderEO);
			msg=mapOrderItemToOrder(orderRequestVO,upOrderEO);
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}

	private String mapOrderItemToOrder(OrderRequestVO orderRequestVO, OrderEO orderEO) {
		List<OrderItemOrderMappingEO> orderItemOrderMappingEOList=new ArrayList<>();
		String msg=null;
		try {
			Set<Long> productCodes=new HashSet<>();
			productCodes=orderRequestVO.getProductCode();
			for (Long code : productCodes) {
				OrderItemOrderMappingEO orderMap=new OrderItemOrderMappingEO();
				orderMap.setOrderId(orderEO.getOrderId());
				orderMap.setProductCode(code);
				orderItemOrderMappingEOList.add(orderMap);
			}
			orderItemOrderMappingRepo.saveAll(orderItemOrderMappingEOList);
			msg="Order added successfully!!";
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}

	@Override
	public OrderResponse getOrderDetails(Long orderId) throws OrderNotFound,Exception {
		OrderResponse orderResponse =new OrderResponse();
		try {
			OrderEO orderEO=orderRepository.findByOrderId(orderId);
			if(Objects.nonNull(orderEO)) {
				OrderVO orderVO=OrderMapper.INSTANCE.fromOrderEOToOrderVO(orderEO);
				orderResponse.setOrderVO(orderVO);
				Set<OrderItemVO> orderItems=getOrderItems(orderId);
				orderResponse.setItemVO(orderItems);
			}else {
				throw new OrderNotFound(HttpStatus.NOT_FOUND, "Order not Found");
			}
			
		} catch (Exception e) {
			throw e;
		}
		return orderResponse;
	}

	private Set<OrderItemVO> getOrderItems(Long orderId) {
		List<OrderItemVO> orderItemVOList=new ArrayList<>();
		Set<OrderItemVO> orederItems=new HashSet<>();
		
		try {
			List<OrderItemOrderMappingEO> mappingList=orderItemOrderMappingRepo.findByOrderId(orderId);
			List<Long> productCodes=mappingList.stream().map(OrderItemOrderMappingEO::getProductCode).collect(Collectors.toList());
			orderItemVOList=orderItemFeignClient.getOrderItemList(productCodes);
			orederItems.addAll(orderItemVOList);
			System.out.println(orederItems);
		} catch (Exception e) {
			throw e;
		}
		return orederItems;
	}

}
