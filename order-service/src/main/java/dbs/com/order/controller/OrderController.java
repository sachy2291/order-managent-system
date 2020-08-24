package dbs.com.order.controller;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbs.com.order.exception.OrderNotFound;
import dbs.com.order.service.OrderService;
import dbs.com.order.vo.OrderRequestVO;
import dbs.com.order.vo.OrderResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "v1/api")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/order-details/{orderId}")
	public OrderResponse getOrderDetails(@PathVariable @Nonnull Long orderId) throws OrderNotFound, Exception {
		logger.info("Order id" + orderId);
		return orderService.getOrderDetails(orderId);

	}

	@PostMapping(value = "/order-details")
	public String addOrder(@Valid @RequestBody @Nonnull OrderRequestVO orderVO) {
		logger.info("OrderRequestVO "+orderVO);
		return orderService.addOrder(orderVO);
	}

}
