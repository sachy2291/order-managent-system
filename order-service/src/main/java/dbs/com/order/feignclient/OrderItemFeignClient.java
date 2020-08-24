package dbs.com.order.feignclient;

import java.util.List;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dbs.com.order.exception.ProductCodeNotFoundException;
import dbs.com.order.vo.OrderItemVO;


@FeignClient("order-item" )
@RequestMapping(value = "v1/api")
public interface OrderItemFeignClient {

	@GetMapping(value="/order-item/{productCode}")
	public OrderItemVO getOrderItems(@PathVariable @NotNull Long productCode)throws ProductCodeNotFoundException ;
	
	@PostMapping(value="/order-item")
	public String addItemDetails(@RequestBody @NotNull OrderItemVO itemVO);
	
	@GetMapping(value="/order-item")
	public List<OrderItemVO> getOrderItemList(@RequestParam @Nonnull List<Long> productCodes)throws ProductCodeNotFoundException;
}