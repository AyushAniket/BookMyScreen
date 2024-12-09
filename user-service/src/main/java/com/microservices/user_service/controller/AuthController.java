package com.microservices.user_service.controller;

import com.microservices.user_service.dto.UserAuthenticationResponse;
import com.microservices.user_service.dto.UserLoginRequest;
import com.microservices.user_service.dto.UserRegisterRequest;
import com.microservices.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserAuthenticationResponse register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return authService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    public UserAuthenticationResponse login(@RequestBody UserLoginRequest userLoginRequest) {
        return authService.login(userLoginRequest);
    }
}
