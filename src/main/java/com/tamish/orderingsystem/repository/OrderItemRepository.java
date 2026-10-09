package com.tamish.orderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.tamish.orderingsystem.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem , Long> {
	
	List<OrderItem> findByOrder_Id(Long id);

}
