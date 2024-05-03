package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helper.ConstantURL.LOGIN_PAGE;
import static org.junit.Assert.assertEquals;

public class LoginPage {
    private final WebDriver driver;
    private final By loginLabel = By.xpath(".//*[text() = 'Вход']");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath(".//button[text() = 'Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание прогрузки страницы авторизации")
    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(loginLabel));
    }

    @Step("Проверка открытия страницы авторизации")
    public void checkLoginPageUrl() {
        assertEquals(LOGIN_PAGE, driver.getCurrentUrl());
    }

    @Step("Заполнить поле 'Email'")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнить поле 'Пароль'")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Авторизация пользователя")
    public void makeLogin(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }


}
