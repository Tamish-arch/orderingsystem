package com.tamish.orderingsystem.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
	
	@NotEmpty
	@Valid
    private List<OrderItemRequest> items;
}