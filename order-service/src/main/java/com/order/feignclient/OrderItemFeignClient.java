package com.order.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.order.vo.OrderItemVO;


@FeignClient("order-item" )
//@RibbonClient("order-item")
@RequestMapping(value = "v1/api")
public interface OrderItemFeignClient {

	@GetMapping(value="/order-items")
	public OrderItemVO getOrderItems(@RequestParam Long productId);
	
	@PostMapping(value="/add-item")
	public String addItem( @RequestBody OrderItemVO itemVO);
	
	@GetMapping(value="/order-items-list")
	public List<OrderItemVO> getOrderItemList(@RequestParam List<Long> productCodes);
}