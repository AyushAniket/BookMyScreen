package com.microservices.movie_service.dto;

public record TicketInformation(String movieName, String saloonName, String movieDay, String movieStartTime,
                                String email, String fullName, String phone, String chairNumbers) {
}
