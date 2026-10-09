package com.tamish.orderingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamish.orderingsystem.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

	List<MenuItem> findByRestaurant_Id(Long restaurantId);

}
