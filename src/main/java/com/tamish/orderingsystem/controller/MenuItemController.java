package com.tamish.orderingsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamish.orderingsystem.entity.MenuItem;
import com.tamish.orderingsystem.service.MenuItemService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/menu-items")
@AllArgsConstructor
public class MenuItemController {

    private MenuItemService menuItemService;

    @PostMapping("/restaurant/{restaurantId}")
    public ResponseEntity<MenuItem> createMenuItem(@PathVariable Long restaurantId,@Valid @RequestBody MenuItem menuItem) {
        return new ResponseEntity<>(menuItemService.createMenuItem(restaurantId, menuItem), HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuItem>> getMenuForRestaurant(@PathVariable Long restaurantId) {
        return new ResponseEntity<>(menuItemService.getMenuForRestaurant(restaurantId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getById(@PathVariable Long id) {
        return new ResponseEntity<>(menuItemService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return new ResponseEntity<>(menuItemService.updateMenuItem(id, menuItem), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
