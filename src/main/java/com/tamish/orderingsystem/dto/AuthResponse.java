package com.tamish.orderingsystem.dto;

public record AuthResponse(String token, String tokenType, long expiresInSeconds) {
}