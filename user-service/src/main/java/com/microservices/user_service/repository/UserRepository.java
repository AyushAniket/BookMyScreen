package com.microservices.user_service.repository;

import com.microservices.user_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUserId(String userId);

    User findUserByEmail(String email);
}
