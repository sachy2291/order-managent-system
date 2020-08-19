package com.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.order.eo.OrderEO;
import com.order.vo.OrderVO;

@Mapper
public interface OrderMapper {
	OrderMapper INSTANCE= Mappers.getMapper(OrderMapper.class);

	OrderVO fromOrderEOToOrderVO(OrderEO orderEO);

	OrderEO fromOrderVOToOrderEO(OrderVO orderVO);

}
