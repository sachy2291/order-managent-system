package dbs.com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dbs.com.order.eo.OrderEO;

@Repository
public interface OrderRepository extends JpaRepository<OrderEO, Long>{

	OrderEO findByOrderId(Long orderId);

}
