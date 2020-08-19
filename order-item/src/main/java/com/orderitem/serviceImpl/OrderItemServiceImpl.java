package com.orderitem.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderitem.eo.OrderItemEO;
import com.orderitem.mapper.ItemMapper;
import com.orderitem.repository.OrderItemRepository;
import com.orderitem.service.OrderItemService;
import com.orderitem.vo.OrderItemVO;
@Service
public class OrderItemServiceImpl implements OrderItemService  {
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	@Transactional(noRollbackFor = Exception.class)
	public OrderItemVO getOrderItems(Long productIds) throws Exception {
		
		OrderItemEO itemListEO=new OrderItemEO();
		OrderItemVO itemListVO=new OrderItemVO();
		try {
			
			itemListEO=orderItemRepository.findByProductCode(productIds);
			itemListVO=ItemMapper.INSTANCE.fromItemEOToItemVO(itemListEO);
		} catch (Exception e) {
			throw e;
		}
		return itemListVO;
	}

	@Override
	@Transactional(noRollbackFor = Exception.class)
	public String addItem( OrderItemVO itemVO) throws Exception {
		String msg=null;
		OrderItemEO  orderItemEO=null;	
		try {
			orderItemEO=new OrderItemEO();
			BeanUtils.copyProperties(itemVO, orderItemEO);
			orderItemRepository.save(orderItemEO);
			msg="Item added";
		} catch (Exception e) {
			throw e;
		}
		
		return msg;
	}

	@Override
	public List<OrderItemVO> getOrderItemList(List<Long> productCodes) throws Exception {
		List<OrderItemVO> itemListVO=new ArrayList<OrderItemVO>();
		List<OrderItemEO> itemListEO=new ArrayList<OrderItemEO>();
		try {
			itemListEO=orderItemRepository.findByProductCodeIn(productCodes);
			itemListVO=ItemMapper.INSTANCE.fromItemListEOToItemListVO(itemListEO);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return itemListVO;
	}

}
