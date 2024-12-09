package com.microservices.movie_service.service;


import com.microservices.movie_service.dto.EmailMessage;
import com.microservices.movie_service.dto.TicketInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final KafkaTemplate<String, EmailMessage> kafkaTemplate;

    public void sendTicketDetail(TicketInformation ticketInformation) {

        EmailMessage emailMessage = EmailMessage.builder()
                .sender("bookMyScreen@gmail.com")
                .recipient(ticketInformation.email())
                .subtitle("BookMyScreen Ticket Detail")
                .fullName(ticketInformation.fullName())
                .movieName(ticketInformation.movieName())
                .movieDay(ticketInformation.movieDay())
                .movieStartTime(ticketInformation.movieStartTime())
                .theaterName(ticketInformation.saloonName())
                .seatNumbers(ticketInformation.chairNumbers())
                .build();

        log.info("Start - Sending Email Message {} to Kafka topic ticket-detail", emailMessage);
        kafkaTemplate.send("ticket-detail", emailMessage);
        log.info("End - Sending Email Message {} to Kafka topic ticket-detail", emailMessage);
    }
}
