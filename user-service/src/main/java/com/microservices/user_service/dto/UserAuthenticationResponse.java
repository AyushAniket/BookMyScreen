package com.microservices.user_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthenticationResponse {
    private String userId;
    private String email;
    private String fullName;
    private List<String> roles;
    private String token;
}
