package com.example.samsungfinalproject;

public class UserManager {
    private static int currentUserId;

    // Установить идентификатор текущего пользователя
    public static void setCurrentUserId(int userId) {
        currentUserId = userId;
    }

    // Получить идентификатор текущего пользователя
    public static int getCurrentUserId() {
        return currentUserId;
    }
}
