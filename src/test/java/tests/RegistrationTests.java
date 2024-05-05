package tests;


import io.qameta.allure.Description;
import org.junit.Test;
import pages.LoginPage;
import pages.RegistrationPage;

import static helper.ConstantURL.REGISTRATION_PAGE;

public class RegistrationTests extends BaseTest {

    private final String name = faker.name().firstName();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password();

    @Test
    @Description("Проверка регистрации нового пользователя: " +
            "Регистрация нового пользователя. " +
            "Ожидание появления лейбла 'Вход'. " +
            "Проверяется переход на страницу 'Логин'. " +
            "Авторизация пользователя для получения токена и последующего удаления пользователя.")
    public void registrationTest() {
        driver.get(REGISTRATION_PAGE);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.makeRegistration(name, email, password);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadLoginPage();
        loginPage.checkLoginPageUrl();

        loginUser(email, password, name);
    }

}

