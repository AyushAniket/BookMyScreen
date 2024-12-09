package com.microservices.movie_service.controller;


import com.microservices.movie_service.dto.TicketInformation;
import com.microservices.movie_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie/payments/")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("sendTicketDetail")
    public void sendTicketDetail(@RequestBody TicketInformation ticketInformation) {
        paymentService.sendTicketDetail(ticketInformation);
    }
}
