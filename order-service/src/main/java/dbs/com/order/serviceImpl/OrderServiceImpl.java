package dbs.com.order.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dbs.com.order.eo.OrderEO;
import dbs.com.order.eo.OrderItemOrderMappingEO;
import dbs.com.order.exception.OrderNotFoundException;
import dbs.com.order.exception.ProductCodeNotFoundException;
import dbs.com.order.feignclient.OrderItemFeignClient;
import dbs.com.order.mapper.OrderMapper;
import dbs.com.order.repository.OrderItemOrderMappingRepo;
import dbs.com.order.repository.OrderRepository;
import dbs.com.order.service.OrderService;
import dbs.com.order.vo.OrderItemVO;
import dbs.com.order.vo.OrderRequestVO;
import dbs.com.order.vo.OrderResponse;
import dbs.com.order.vo.OrderVO;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemFeignClient orderItemFeignClient;
	
	@Autowired
	private OrderItemOrderMappingRepo orderItemOrderMappingRepo;

	@Override
	public String addOrder(OrderRequestVO orderRequestVO){
			OrderVO orderVO=new OrderVO();
			orderVO=orderRequestVO.getOrderVO();
			OrderEO orderEO=OrderMapper.INSTANCE.fromOrderVOToOrderEO(orderVO);
			OrderEO upOrderEO=orderRepository.save(orderEO);
			String msg=mapOrderItemToOrder(orderRequestVO,upOrderEO);
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
	public OrderResponse getOrderDetails(Long orderId) throws OrderNotFoundException, ProductCodeNotFoundException {
		OrderResponse orderResponse =new OrderResponse();
			OrderEO orderEO= orderRepository.findByOrderId(orderId);
			if(Objects.nonNull(orderEO)) {
				OrderVO orderVO=OrderMapper.INSTANCE.fromOrderEOToOrderVO(orderEO);
				orderResponse.setOrderVO(orderVO);
				Set<OrderItemVO> orderItems=getOrderItems(orderId);
				orderResponse.setItemVO(orderItems);
			}else {
				throw new OrderNotFoundException("Order id is not Found");
			}
			
		return orderResponse;
	}

	private Set<OrderItemVO> getOrderItems(Long orderId) throws OrderNotFoundException, ProductCodeNotFoundException {
		List<OrderItemVO> orderItemVOList=new ArrayList<>();
		Set<OrderItemVO> orderItems=new HashSet<>();
		
			List<OrderItemOrderMappingEO> mappingList=orderItemOrderMappingRepo.findByOrderId(orderId);
			if(Objects.nonNull(mappingList)) {
				List<Long> productCodes=mappingList.stream().map(OrderItemOrderMappingEO::getProductCode).collect(Collectors.toList());
				orderItemVOList=orderItemFeignClient.getOrderItemList(productCodes);
				orderItems.addAll(orderItemVOList);
			}else {
				throw new ProductCodeNotFoundException("order id is not found");
			}
			
		return orderItems;
	}

}
