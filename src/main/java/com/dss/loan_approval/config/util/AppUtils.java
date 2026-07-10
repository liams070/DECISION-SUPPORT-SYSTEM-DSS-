package com.dss.loan_approval.config.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class AppUtils {

    private AppUtils() {}

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String generateOtp() {
        return generateNumericCode(6);
    }

    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now(ZoneId.of("Africa/Lagos"));
    }

    public static String generateNumericCode(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive.");
        }
        Random random = new Random();
        int firstDigit = random.nextInt(9) + 1;
        String rest = IntStream.range(1, length)
                .map(i -> random.nextInt(10))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return firstDigit + rest;
    }

    public static <T> T jsonStringToObject(String data, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(data, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }
}
