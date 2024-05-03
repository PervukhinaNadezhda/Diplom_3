package helper.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserMethods {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static final String USER_REGISTER_PATH = "/api/auth/register";
    private static final String USER_LOGIN_PATH = "/api/auth/login";
    private static final String USER_UPDATE_PATH = "/api/auth/user";

    @Step("Создать пользователя")
    public static Response createUser(String email, String password, String name) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(new User(email, password, name))
                .when()
                .post(USER_REGISTER_PATH);
    }

    @Step("Логин пользователя")
    public static Response loginUser(String email, String password, String name) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(new User(email, password, name))
                .when()
                .post(USER_LOGIN_PATH);
    }

    @Step("Удалить пользователя")
    public static void deleteUser(String accessToken) {
        given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete(USER_UPDATE_PATH);
    }

}
