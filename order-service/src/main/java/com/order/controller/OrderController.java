package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.exception.OrderNotFound;
import com.order.service.OrderService;
import com.order.vo.OrderRequestVO;
import com.order.vo.OrderResponse;

@CrossOrigin(origins = "*"/*, allowedHeaders = "*"*/)
@RestController
@RequestMapping(value = "v1/api")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping(value="/order-details")
	public OrderResponse getOrderDetails(@RequestParam Long orderId) throws OrderNotFound, Exception{
		
		return orderService.getOrderDetails(orderId);
		
	}
	@PostMapping(value="/add-order")
	public String addOrder( @RequestBody OrderRequestVO orderVO) {
		String msg=null;
		try {
			msg = orderService.addOrder(orderVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}

}
