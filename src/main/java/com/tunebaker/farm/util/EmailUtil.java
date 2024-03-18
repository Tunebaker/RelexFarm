package com.tunebaker.farm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunebaker.farm.model.EmailMessage;
import com.tunebaker.farm.model.entity.Product;

import java.util.Map;

public class EmailUtil {

    private final static String EMAIL_TO = "alandrr@ya.ru";
    private static final String SUBJECT = "Отчет по ферме за текущий день";
    private static final String TEXT_TEMPLATE = "За сегодня на ферме произведено: %s";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static EmailMessage constructEmail(Map<Product, Float> report) {
        String text = String.format(TEXT_TEMPLATE, report);
        EmailMessage message = new EmailMessage(EMAIL_TO, SUBJECT, text);
        return message;
    }

    public static String getSerialized(EmailMessage message)  {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static EmailMessage deserialize(String s)  {
        try {
            return objectMapper.readValue(s, EmailMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
