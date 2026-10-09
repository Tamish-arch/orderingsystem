package com.tamish.orderingsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamish.orderingsystem.dto.CreateOrderRequest;
import com.tamish.orderingsystem.entity.Order;
import com.tamish.orderingsystem.entity.OrderItem;
import com.tamish.orderingsystem.enums.Status;
import com.tamish.orderingsystem.service.OrderService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody CreateOrderRequest request,
                                             Authentication authentication) {
        Order order = orderService.createOrder(authentication.getName(), request.getItems());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<OrderItem>> getItemsForOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getItemsForOrder(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id, @RequestBody Status status) {
        return new ResponseEntity<>(orderService.updateStatus(id, status), HttpStatus.OK);
    }
}
