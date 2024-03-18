package com.tunebaker.farm.service;

import com.tunebaker.farm.model.EmailMessage;
import com.tunebaker.farm.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableKafka
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;

    @KafkaListener(topics = "farm-notification")
    @Override
    public void sendEmail(String message) {

        log.info("Принято из Kafka {}", message);

        EmailMessage deserialized = EmailUtil.deserialize(message);

        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(deserialized.getTo());
        emailMessage.setSubject(deserialized.getSubject());
        emailMessage.setText(deserialized.getText());

        log.info("отправляется e-mail сообщение {}", emailMessage);
        try {
            mailSender.send(emailMessage);
            log.info("Сообщение email отправлено");
        } catch (RuntimeException e) {
            log.error("Ошибка отправки email сообщения: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
