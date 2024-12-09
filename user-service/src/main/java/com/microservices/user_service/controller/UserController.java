package com.microservices.user_service.controller;

import com.microservices.user_service.dto.UserRegisterRequest;
import com.microservices.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("isExist/{userId}")
    public boolean isExists(@PathVariable String userId) {
        return userService.isUserExist(userId);
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.addUser(userRegisterRequest);
    }

    @GetMapping("isUserCustomer")
    public boolean isUserCustomer() {
        return userService.isUserCustomer();
    }
    @GetMapping("isUserAdmin")
    public boolean isUserAdmin() {
        return userService.isUserAdmin();
    }
}
