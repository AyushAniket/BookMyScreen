package com.microservices.user_service.repository;

import com.microservices.user_service.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role getRoleByRoleName(String roleName);
}
