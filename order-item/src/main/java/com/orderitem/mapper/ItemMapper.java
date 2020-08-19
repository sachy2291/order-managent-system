package com.orderitem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.orderitem.eo.OrderItemEO;
import com.orderitem.vo.OrderItemVO;

@Mapper
public interface ItemMapper {
	ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);


	OrderItemVO fromItemEOToItemVO(OrderItemEO itemListEO);


	List<OrderItemVO> fromItemListEOToItemListVO(List<OrderItemEO> itemListEO);
}
