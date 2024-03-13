package model;

// Модель данных для пользователя.
public class User {
    private String name; // Имя пользователя.
    private String email; // email пользователя.
    private String password; // Пароль пользователя.

    // Конструктор для инициализации пользователя.
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Геттер для имени пользователя.
    public String getName() {
        return name;
    }

    // Сеттер для имени пользователя.
    public void setName(String name) {
        this.name = name;
    }

    // Геттер для email пользователя.
    public String getEmail() {
        return email;
    }

    // Сеттер для email пользователя.
    public void setEmail(String email) {
        this.email = email;
    }

    // Геттер для пароля пользователя.
    public String getPassword() {
        return password;
    }

    // Сеттер для пароля пользователя.
    public void setPassword(String password) {
        this.password = password;
    }
}
