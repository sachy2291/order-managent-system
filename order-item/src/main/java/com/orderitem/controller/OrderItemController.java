package com.orderitem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderitem.service.OrderItemService;
import com.orderitem.vo.OrderItemVO;

@CrossOrigin(origins = "*"/*, allowedHeaders = "*"*/)
@RestController
@RequestMapping(value = "v1/api")
public class OrderItemController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping(value="/order-items")
	public OrderItemVO getOrderItems(@RequestParam Long productId){
		OrderItemVO itemListVO=new OrderItemVO();
		try {
			itemListVO=orderItemService.getOrderItems(productId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemListVO;
		
	}
	@PostMapping(value="/add-item")
	public String addItem( @RequestBody OrderItemVO itemVO) {
		String msg=null;
		try {
			msg = orderItemService.addItem(itemVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@GetMapping(value="/order-items-list")
	public List<OrderItemVO> getOrderItemList(@RequestParam List<Long> productCodes){
		List<OrderItemVO> orderItemVOList=new ArrayList<>();;
		try {
			orderItemVOList=orderItemService.getOrderItemList(productCodes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItemVOList;
		
	}
}
