package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import pageObject.ConstructorPage;
import pageObject.LoginPage;
import pageObject.ProfilePage;

import static helper.ConstantURL.LOGIN_PAGE;
import static helper.Waiter.waitFor;

public class ProfileTests extends BaseTest {

    private final String name = faker.name().firstName();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password();

    @Test
    @Description("Проверка открытия страницы профиля авторизированного пользователя: " +
            "Авторизация под существующим пользователем. " +
            "Переход на страницу профиля после нажатия кнопки 'Личный Кабинет'. " +
            "Ожидание появления лейбла 'Профиль'. " +
            "Проверяется переход на страницу 'Профиль'.")
    public void profilePageOpenTest() {
        userPrepare();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadProfilePage();
        profilePage.checkProfilePageUrl();
    }

    @Test
    @Description("Проверка выхода из профиля авторизированного пользователя: " +
            "Авторизация под существующим пользователем. " +
            "Переход на страницу профиля. " +
            "Выход из аккаунта. " +
            "Ожидание появления лейбла 'Вход'. " +
            "Проверяется переход на страницу 'Логин'. ")
    public void profileLogoutTest() {
        userPrepare();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadProfilePage();
        profilePage.clickLogoutButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadLoginPage();
        loginPage.checkLoginPageUrl();
    }

    @Test
    @Description("Проверка перехода из профиля пользователя на страницу 'Конструктор' нажатием кнопки логотипа: " +
            "Авторизация под существующим пользователем. " +
            "Переход на страницу профиля. " +
            "Переход на страницу 'Конструктор' после нажатия кнопки логотипа. " +
            "Ожидание появления лейбла 'Соберите бургер'. " +
            "Проверяется переход на страницу 'Конструктор'.")
    public void profilePageToConstructorPageByLogoButtonTest() {
        userPrepare();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoButton();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitForLoadConstructorPage();
        constructorPage.checkConstructorPageUrl();
    }

    @Test
    @Description("Проверка перехода из профиля пользователя на страницу 'Конструктор' нажатием кнопки 'Конструктор': " +
            "Авторизация под существующим пользователем. " +
            "Переход на страницу профиля. " +
            "Переход на страницу 'Конструктор' после нажатия кнопки 'Конструктор'. " +
            "Ожидание появления лейбла 'Соберите бургер'. " +
            "Проверяется переход на страницу 'Конструктор'. ")
    public void profilePageToConstructorPageByConstructorButtonTest() {
        userPrepare();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickConstructorButton();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitForLoadConstructorPage();
        constructorPage.checkConstructorPageUrl();
    }

    public void userPrepare() {
        createUser(email, password, name);

        driver.get(LOGIN_PAGE);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.makeLogin(email, password);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitForLoadConstructorPage();
        constructorPage.clickPersonalAccountButton();
    }

}
