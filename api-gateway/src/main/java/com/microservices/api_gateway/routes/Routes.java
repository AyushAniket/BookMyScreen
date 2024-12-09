package com.microservices.api_gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("movie-service", r -> r
                        .path("/api/movie/**")
                        .uri("lb://movie-service"))
                .route("user-service", r -> r
                        .path("/api/user/**")
                        .uri("lb://user-service"))
                .route("notification-service", r -> r
                        .path("/api/notification/**")
                        .uri("lb://notification-service"))
                .build();
    }
}
