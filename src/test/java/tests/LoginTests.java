package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObject.ConstructorPage;
import pageObject.ForgotPasswordPage;
import pageObject.LoginPage;
import pageObject.RegistrationPage;

import static helper.ConstantURL.*;

@RunWith(Parameterized.class)
public class LoginTests extends BaseTest {

    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password();
    private final String name = faker.name().firstName();
    private final String buttonPlace;

    public LoginTests(String buttonPlace) {
        this.buttonPlace = buttonPlace;
    }

    @Parameterized.Parameters(name = "[{index}]:  {0}")
    public static Object[][] getTopPageOrderButton() {
        return new Object[][]{
                {"Проверка авторизации по кнопке 'Войти в аккаунт' на главной странице"},
                {"Проверка авторизации по кнопке 'Личный Кабинет' на главной странице"},
                {"Проверка авторизации по кнопке 'Войти' на странице регистрации"},
                {"Проверка авторизации по кнопке 'Войти' на странице восстановления пароля"},
        };
    }

    @Test
    @Description("Проверка входа в аккаунт: " +
            "Переход на страницу авторизации. " +
            "Авторизация под существующим пользователем. " +
            "Ожидание появления лейбла 'Соберите бургер'. " +
            "Проверяется переход на страницу 'Конструктор'.")
    public void loginByLoginButton() {
        createUser(email, password, name);

        clickLoginButton(buttonPlace);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadLoginPage();
        loginPage.checkLoginPageUrl();
        loginPage.makeLogin(email, password);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitForLoadConstructorPage();
        constructorPage.checkConstructorPageUrl();
    }


    public void clickLoginButton(String buttonPlace) {
        switch (buttonPlace) {
            case "Проверка авторизации по кнопке 'Войти в аккаунт' на главной странице": {
                driver.get(CONSTRUCTOR_PAGE);
                ConstructorPage constructorPage = new ConstructorPage(driver);
                constructorPage.clickLoginAccountButton();
                break;
            }
            case "Проверка авторизации по кнопке 'Личный Кабинет' на главной странице": {
                driver.get(CONSTRUCTOR_PAGE);
                ConstructorPage constructorPage = new ConstructorPage(driver);
                constructorPage.clickPersonalAccountButton();
                break;
            }
            case "Проверка авторизации по кнопке 'Войти' на странице регистрации": {
                driver.get(REGISTRATION_PAGE);
                RegistrationPage registrationPage = new RegistrationPage(driver);
                registrationPage.clickLoginButton();
                break;
            }
            case "Проверка авторизации по кнопке 'Войти' на странице восстановления пароля": {
                driver.get(FORGOT_PASSWORD_PAGE);
                ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
                forgotPasswordPage.clickLoginButton();
                break;
            }
        }
    }

}
