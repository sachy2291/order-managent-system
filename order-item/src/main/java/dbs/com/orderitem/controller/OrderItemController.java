package dbs.com.orderitem.controller;

import java.util.List;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dbs.com.orderitem.exception.ProductCodeNotFoundException;
import dbs.com.orderitem.service.OrderItemService;
import dbs.com.orderitem.vo.OrderItemVO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "v1/api")
public class OrderItemController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);
	
	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping(value="/order-item/{productCode}")
	public OrderItemVO getOrderItems(@PathVariable @NotNull Long productCode)throws ProductCodeNotFoundException {
		logger.info("product code "+productCode);
		return orderItemService.getOrderItems(productCode);
		
	}
	@PostMapping(value="/order-item")
	public String addItemDetails(@RequestBody @NotNull OrderItemVO itemVO) {
		logger.info("item request body "+itemVO);	
		return orderItemService.addItem(itemVO);
	}
	
	@GetMapping(value="/order-item")
	public List<OrderItemVO> getOrderItemList(@RequestParam @Nonnull List<Long> productCodes)throws ProductCodeNotFoundException{
		return orderItemService.getOrderItemList(productCodes);
		
	}
}
