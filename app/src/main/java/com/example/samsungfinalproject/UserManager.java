package com.example.samsungfinalproject;

public class UserManager {
    private static int currentUserId;

    public static void setCurrentUserId(int userId) {
        currentUserId = userId;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }
}
