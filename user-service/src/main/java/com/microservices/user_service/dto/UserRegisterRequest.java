package com.microservices.user_service.dto;

public record UserRegisterRequest( String customerName, String email, String phone, String password) {
}
