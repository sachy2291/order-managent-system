package dbs.com.orderitem.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dbs.com.orderitem.controller.OrderItemController;
import dbs.com.orderitem.eo.OrderItemEO;
import dbs.com.orderitem.exception.ProductCodeNotFoundException;
import dbs.com.orderitem.mapper.ItemMapper;
import dbs.com.orderitem.repository.OrderItemRepository;
import dbs.com.orderitem.service.OrderItemService;
import dbs.com.orderitem.vo.OrderItemVO;
@Service
public class OrderItemServiceImpl implements OrderItemService  {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	@Transactional(noRollbackFor = Exception.class)
	public OrderItemVO getOrderItems(Long productCode) throws ProductCodeNotFoundException {
		logger.info("product code "+productCode);
		OrderItemEO itemListEO = new OrderItemEO();
		OrderItemVO itemListVO = new OrderItemVO();

		itemListEO = orderItemRepository.findByProductCode(productCode);
		if (Objects.nonNull(itemListEO)) {
			itemListVO = ItemMapper.INSTANCE.fromItemEOToItemVO(itemListEO);
		} else {
			throw new ProductCodeNotFoundException("Product code not found");
		}

		return itemListVO;
	}

	@Override
	@Transactional(noRollbackFor = Exception.class)
	public String addItem(OrderItemVO itemVO) {
		logger.info("product name "+itemVO.getProductName());
		String msg = null;
		OrderItemEO orderItemEO = null;
		orderItemEO = new OrderItemEO();
		BeanUtils.copyProperties(itemVO, orderItemEO);
		orderItemRepository.save(orderItemEO);
		msg = "Item added";
		return msg;
	}

	@Override
	public List<OrderItemVO> getOrderItemList(List<Long> productCodes) throws ProductCodeNotFoundException {
		List<OrderItemVO> itemListVO=new ArrayList<OrderItemVO>();
		List<OrderItemEO> itemListEO=orderItemRepository.findByProductCodeIn(productCodes);
			if (itemListEO.size()!=0) {
				itemListVO=ItemMapper.INSTANCE.fromItemListEOToItemListVO(itemListEO);
			}else {
				throw new ProductCodeNotFoundException("Product code not found");
			}
		return itemListVO;
	}

}
