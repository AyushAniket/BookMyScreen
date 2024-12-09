package com.microservices.user_service.service;

import com.microservices.user_service.dto.UserAuthenticationResponse;
import com.microservices.user_service.dto.UserLoginRequest;
import com.microservices.user_service.dto.UserRegisterRequest;
import com.microservices.user_service.model.User;
import com.microservices.user_service.security.JwtProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProviderService jwtProvider;
    private final UserService userService;

    public UserAuthenticationResponse register(UserRegisterRequest userRegisterRequest) {
        // Register the user
        userService.addUser(userRegisterRequest);

        // After registration, log them in automatically
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRegisterRequest.email(),
                userRegisterRequest.password()
        ));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);

        User user = userService.getUserByEmail(userRegisterRequest.email());

        return UserAuthenticationResponse.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(userRegisterRequest.email())
                .token(token)
                .roles(authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .build();
    }

    public UserAuthenticationResponse login(UserLoginRequest userLoginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginRequest.email(),
                userLoginRequest.password()
        ));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);

        User user = userService.getUserByEmail(userLoginRequest.email());

        return UserAuthenticationResponse.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(userLoginRequest.email())
                .token(token)
                .roles(authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .build();
    }
}
