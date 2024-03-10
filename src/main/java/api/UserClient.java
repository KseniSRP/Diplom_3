package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;
import model.UserResponse;
import org.hamcrest.CoreMatchers;

public class UserClient {

    @Step("Создание пользователя")
    public UserResponse createUser(User user) {
        // Отправляем запрос на создание пользователя
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register")
                .then()
                // Получаем ответ
                .statusCode(200)
                .body("success", CoreMatchers.equalTo(true))
                .extract()
                .response(); // Извлекаем полный ответ для дальнейшей работы

        // Извлекаем токен из ответа.
        String accessToken = response.path("accessToken");

        // Возвращаем новый объект UserResponse, содержащий пользователя и токен
        return new UserResponse(user, accessToken);
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        RestAssured.given()
                .header("Authorization", accessToken) // Используем токен доступа в заголовке Authorization
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(202)
                .body("success", CoreMatchers.equalTo(true));
    }
}