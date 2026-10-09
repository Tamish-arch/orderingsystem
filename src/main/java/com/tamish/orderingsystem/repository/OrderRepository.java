package com.tamish.orderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamish.orderingsystem.entity.Order;

public interface OrderRepository extends JpaRepository<Order , Long> {

}
