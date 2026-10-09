package com.tamish.orderingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamish.orderingsystem.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long > {

   Optional<Restaurant> findByName(String name);
	
}
