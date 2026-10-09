package com.tamish.orderingsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tamish.orderingsystem.entity.Restaurant;
import com.tamish.orderingsystem.exceptions.ResourceNotFoundException;
import com.tamish.orderingsystem.repository.RestaurantRepository;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor
public class RestaurantService {
	
	private RestaurantRepository restaurantRepo;
	
	public Restaurant createRestaurant(Restaurant restaurant) {
		
		String name = restaurant.getName();
		
       if(restaurantRepo.findByName(name).isPresent()) {
    	   
    	   throw new RuntimeException("Restaurant Already exists");
    	   
       }
    	   
    	   Restaurant res = new Restaurant();
    	   res.setName(name);
    	   res.setAddress(restaurant.getAddress());
         
        return restaurantRepo.save(res);
		
	}
	public List<Restaurant> getAllRestaurants(){
		
		return restaurantRepo.findAll();
		
	}
	
	
	public Restaurant getById(Long id) {
		
		return restaurantRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Restaurant Not found."));
			
		}		
	
	public void deleteRestaurant(Long id) {
		if(!restaurantRepo.existsById(id)) {
			throw new ResourceNotFoundException("Restaurant Not Found");
		}else
		restaurantRepo.deleteById(id);
		
	}
	
	public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
		Restaurant res = restaurantRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Restaurant Not Found") );
		if(restaurant.getName()!=null && !restaurant.getName().isBlank()) {
		
			res.setName(restaurant.getName());
			}
		if(restaurant.getAddress()!=null && !restaurant.getAddress().isBlank())
		{
			
		    res.setAddress(restaurant.getAddress());
		}
		
		return restaurantRepo.save(res);
	
		
		
		
	}

}
