package com.tunebaker.farm.service;

import com.tunebaker.farm.model.EmailMessage;
import com.tunebaker.farm.model.entity.Product;
import com.tunebaker.farm.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {
    private final KafkaTemplate<Void, String> kafkaTemplate;

    public void sendDaySum(Map<Product, Float> productDaySum) {

        EmailMessage message = EmailUtil.constructEmail(productDaySum);
        String serializedMessage = EmailUtil.getSerialized(message);
        log.info("Отправлено в Kafka: {}" , serializedMessage);
        kafkaTemplate.send("farm-notification", serializedMessage);
    }
}
