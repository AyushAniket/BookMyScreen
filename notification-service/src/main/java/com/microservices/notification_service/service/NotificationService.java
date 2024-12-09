package com.microservices.notification_service.service;

import com.microservices.notification_service.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "ticket-detail")
    public void listen(EmailMessage emailMessage) {
        log.info("Got Email Message from ticket-detail topic {}", emailMessage);
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(emailMessage.getSender());
            messageHelper.setTo(emailMessage.getRecipient());
            messageHelper.setSubject(String.format("Your Ticket for  %s is booked successfully", emailMessage.getMovieName()));
            messageHelper.setText(String.format("""
                            Hi %s,
                            
                            Your ticket details for the movie %s are as follows:
                            
                            Theatre: %s
                            Day: %s
                            Seat: %s
                            Start Time: %s
                            
                            Best Regards
                            BookMyScreen Team
                            """,
                    emailMessage.getFullName(),
                    emailMessage.getMovieName(),
                    emailMessage.getTheaterName(),
                    emailMessage.getMovieDay(),
                    emailMessage.getSeatNumbers(),
                    emailMessage.getMovieStartTime()));
        };
        try {
            javaMailSender.send(messagePreparator);
            log.info("Ticket Detail email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new RuntimeException("Exception occurred when sending mail", e);
        }
    }
}
