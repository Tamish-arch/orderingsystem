package com.tamish.orderingsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.tamish.orderingsystem.entity.MenuItem;
import com.tamish.orderingsystem.entity.Restaurant;
import com.tamish.orderingsystem.exceptions.ResourceNotFoundException;
import com.tamish.orderingsystem.repository.MenuItemRepository;
import com.tamish.orderingsystem.repository.RestaurantRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MenuItemService {

    private MenuItemRepository menuItemRepo;
    private RestaurantRepository restaurantRepo;

    public MenuItem createMenuItem(Long restaurantId, MenuItem menuItem) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        MenuItem item = new MenuItem();
        item.setItemName(menuItem.getItemName());
        item.setPrice(menuItem.getPrice());
        item.setRestaurant(restaurant);
        return menuItemRepo.save(item);
    }

    public List<MenuItem> getMenuForRestaurant(Long restaurantId) {
        if (!restaurantRepo.existsById(restaurantId)) {
            throw new ResourceNotFoundException("Restaurant not found");
        }
        return menuItemRepo.findByRestaurant_Id(restaurantId);
    }

    public MenuItem getById(Long id) {
        return menuItemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
    }

    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        MenuItem item = menuItemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
        if (menuItem.getItemName() != null && !menuItem.getItemName().isBlank()) {
            item.setItemName(menuItem.getItemName());
        }
        if (menuItem.getPrice() > 0) {
            item.setPrice(menuItem.getPrice());
        }
        return menuItemRepo.save(item);
    }

    public void deleteMenuItem(Long id) {
        if (!menuItemRepo.existsById(id)) {
            throw new ResourceNotFoundException("Menu item not found");
        }
        menuItemRepo.deleteById(id);
    }
}