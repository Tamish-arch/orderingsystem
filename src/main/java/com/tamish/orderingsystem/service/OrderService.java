package com.tamish.orderingsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tamish.orderingsystem.dto.OrderItemRequest;
import com.tamish.orderingsystem.entity.MenuItem;
import com.tamish.orderingsystem.entity.Order;
import com.tamish.orderingsystem.entity.OrderItem;
import com.tamish.orderingsystem.enums.Status;
import com.tamish.orderingsystem.exceptions.ResourceNotFoundException;
import com.tamish.orderingsystem.repository.MenuItemRepository;
import com.tamish.orderingsystem.repository.OrderItemRepository;
import com.tamish.orderingsystem.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {

	private OrderRepository orderRepo;
	private OrderItemRepository orderItemRepo;
	private MenuItemRepository menuItemRepo;

	@Transactional
	public Order createOrder(String customer, List<OrderItemRequest> items) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setStatus(Status.ACCEPTED);
		Order savedOrder = orderRepo.save(order);

		for (OrderItemRequest itemReq : items) {
			MenuItem menuItem = menuItemRepo.findById(itemReq.getMenuItemId()).orElseThrow(
					() -> new ResourceNotFoundException("Menu item not found: " + itemReq.getMenuItemId()));

			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(savedOrder);
			orderItem.setMenuItem(menuItem);
			orderItem.setQuantity(itemReq.getQuantity());
			orderItem.setPriceAtOrderTime(menuItem.getPrice());
			orderItemRepo.save(orderItem);
		}

		return savedOrder;
	}

	public List<OrderItem> getItemsForOrder(Long orderId) {
		if (!orderRepo.existsById(orderId)) {
			throw new ResourceNotFoundException("Order not found");
		}
		return orderItemRepo.findByOrder_Id(orderId);
	}

	public Order getById(Long id) {
		return orderRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
	}

	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	public Order updateStatus(Long id, Status status) {
		Order order = orderRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
		order.setStatus(status);
		return orderRepo.save(order);
	}
}