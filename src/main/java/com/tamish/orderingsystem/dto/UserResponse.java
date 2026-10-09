package com.tamish.orderingsystem.dto;

import com.tamish.orderingsystem.enums.Role;

public record UserResponse(Long id, String username, Role role) {
}