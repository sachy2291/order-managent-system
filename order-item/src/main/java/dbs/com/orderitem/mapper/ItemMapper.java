package dbs.com.orderitem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dbs.com.orderitem.eo.OrderItemEO;
import dbs.com.orderitem.vo.OrderItemVO;

@Mapper
public interface ItemMapper {
	ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);


	OrderItemVO fromItemEOToItemVO(OrderItemEO itemListEO);


	List<OrderItemVO> fromItemListEOToItemListVO(List<OrderItemEO> itemListEO);
}
