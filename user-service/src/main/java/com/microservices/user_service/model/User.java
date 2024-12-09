package com.microservices.user_service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class User {
    @Id
    private String userId;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String fullName;
    private Role role;
}
