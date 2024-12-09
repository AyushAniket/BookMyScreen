package com.microservices.user_service.service;

import com.microservices.user_service.dto.UserRegisterRequest;
import com.microservices.user_service.exception.EmailAlreadyExistsException;
import com.microservices.user_service.model.Role;
import com.microservices.user_service.model.User;
import com.microservices.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public Boolean isUserExist(String userId) {

        User user = userRepository.findUserByUserId(userId);
        return user != null;
    }

    public void addUser(UserRegisterRequest userRegisterRequest) {
        // Check if email already exists
        User existingUser = getUserByEmail(userRegisterRequest.email());
        if (existingUser != null) {
            throw new EmailAlreadyExistsException(userRegisterRequest.email());
        }

        Role role = roleService.getRoleByRoleName("CUSTOMER");

        User user = User.builder()
                .email(userRegisterRequest.email())
                .password(passwordEncoder.encode(userRegisterRequest.password()))
                .fullName(userRegisterRequest.customerName())
                .role(role)
                .build();
        userRepository.insert(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean isUserCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().allMatch(
                a -> a.getAuthority().equals("ROLE_CUSTOMER")
        );
    }

    public boolean isUserAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_ADMIN")
        );
    }
}
