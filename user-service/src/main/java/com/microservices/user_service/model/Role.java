package com.microservices.user_service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Role {
    @Id
    private String roleId;
    private String roleName;
}
