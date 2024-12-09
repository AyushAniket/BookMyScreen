package com.microservices.movie_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailMessage{
    private String sender;
    private String recipient;
    private String subtitle;
    private String movieName;
    private String theaterName;
    private String movieDay;
    private String movieStartTime;
    private String fullName;
    private String seatNumbers;
}