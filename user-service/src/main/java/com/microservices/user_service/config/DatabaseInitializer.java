package com.microservices.user_service.config;

import com.microservices.user_service.model.Role;
import com.microservices.user_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        // Initialize roles if they don't exist
        if (roleRepository.getRoleByRoleName("CUSTOMER") == null) {
            Role customerRole = Role.builder()
                    .roleName("CUSTOMER")
                    .build();
            roleRepository.save(customerRole);
        }

        if (roleRepository.getRoleByRoleName("ADMIN") == null) {
            Role adminRole = Role.builder()
                    .roleName("ADMIN")
                    .build();
            roleRepository.save(adminRole);
        }
    }
}
