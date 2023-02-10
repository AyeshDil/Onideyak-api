package com.myproject.onideyak.onideyakapi.util;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Generator {

    private static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String numeric = "0123456789";

    public String generatePrefix(int length) {
        // Generate a prefix => PEHCXLN
//        int generatedRandom = generateRandom(length);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            builder.append(letters.charAt(generateRandom(letters.length() - 1)));
        }

        return builder.toString();
    }

    private int generateRandom(int bound) {
        // Generate a random number
        return new Random().nextInt(bound);
    }

    public String generatedPrimaryKey(String prefix, String pointer, int length) {
        // PEHCXLN-U-546943

        StringBuilder builder = new StringBuilder();
        builder.append(prefix);
        builder.append("-" + pointer + "-");

        for (int i = 0; i < length; i++) {
            builder.append(numeric.charAt(generateRandom(numeric.length() - 1)));
        }

        return builder.toString();

    }

    public String generateVerificationCode() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(numeric.charAt(generateRandom(numeric.length() - 1)));
        }
        return builder.toString();
    }
}
