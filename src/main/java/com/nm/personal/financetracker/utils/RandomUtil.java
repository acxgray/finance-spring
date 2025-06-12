package com.nm.personal.financetracker.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomUtil {

    public static String generateRandomString() {
        long timestamp = System.currentTimeMillis();
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        String uniqueId = timestamp + "" + randomNumber;
        return uniqueId;
    }
}
