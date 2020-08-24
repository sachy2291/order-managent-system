package dbs.com.order.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dbs.com.order.eo.OrderItemOrderMappingEO;
@Repository
public interface OrderItemOrderMappingRepo extends JpaRepository<OrderItemOrderMappingEO, Long> {

	List<OrderItemOrderMappingEO> findByOrderId(Long orderId);

}
