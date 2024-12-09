package com.microservices.user_service.service;

import com.microservices.user_service.model.Role;
import com.microservices.user_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleByRoleName(String roleName) {
        return roleRepository.getRoleByRoleName(roleName);
    }
}
