package com.example.samsungfinalproject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class PasswordUtils {

    private static final String SALT = "randomSalt"; // You can generate a salt and store it securely

    public static String hashPassword(String password) {
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        return hashedPassword;
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        String saltedPassword = SALT + password;
        String hashedAttempt = generateHash(saltedPassword);
        return hashedAttempt.equals(hashedPassword);
    }

    private static String generateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
