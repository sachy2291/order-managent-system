package com.orderitem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderitem.eo.OrderItemEO;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEO, Long>{

	List<OrderItemEO> findByProductCodeIn(List<Long> productCodes);

	OrderItemEO findByProductCode(Long productIds);

}
