package model;

// Модель ответа при работе с пользователями, содержит данные пользователя и токен доступа.
public class UserResponse {
    private User user; // Данные пользователя.
    private String accessToken; // Токен доступа.

    // Конструктор для инициализации ответа пользователя.
    public UserResponse(User user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    // Геттер для получения данных пользователя.
    public User getUser() {
        return user;
    }

    // Сеттер для установки данных пользователя.
    public void setUser(User user) {
        this.user = user;
    }

    // Геттер для получения токена доступа.
    public String getAccessToken() {
        return accessToken;
    }

    // Сеттер для установки токена доступа.
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
