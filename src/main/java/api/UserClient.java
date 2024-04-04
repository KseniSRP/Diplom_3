package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;
import model.UserResponse;
import org.hamcrest.CoreMatchers;

public class UserClient extends BaseAPI {
    private static final String REGISTER_ENDPOINT = "/api/auth/register";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String USER_ENDPOINT = "/api/auth/user";

    @Step("Создание пользователя")
    public UserResponse createUser(User user) {
        // Использую метод из BaseAPI для отправки запроса на создание пользователя
        Response response = sendRequest("POST", REGISTER_ENDPOINT, user, null, 200);

        // Проверяю успешность, измлекаю токен
        response.then().body("success", CoreMatchers.equalTo(true));
        String accessToken = response.then().extract().path("accessToken");

        // Возвращаю UserResponse (пользователь и токен)
        return new UserResponse(user, accessToken);
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        // Использую метод из BaseAPI для отправки запроса на удаление пользователя
        sendRequest("DELETE", USER_ENDPOINT, null, accessToken, 202)
                .then().body("success", CoreMatchers.equalTo(true));
    }

    @Step("Логин пользователя и возвращение токена")
    public String loginUserAndGetToken(User user) {
        Response response = sendRequest("POST", LOGIN_ENDPOINT, user, null, 200);
        response.then().body("success", CoreMatchers.equalTo(true));
        return response.then().extract().path("accessToken");
    }
}
