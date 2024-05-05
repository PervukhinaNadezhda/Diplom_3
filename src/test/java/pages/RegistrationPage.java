package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class RegistrationPage {

    private final WebDriver driver;
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By passwordErrorText = By.className("input__error");
    private final By loginButton = By.className("Auth_link__1fOlj");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнить поле 'Имя'")
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Заполнить поле 'Email'")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнить поле 'Пароль'")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Регистрация пользователя")
    public void makeRegistration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
    }

    @Step("Проверка состояния ошибки сохранения пароля")
    public void checkPasswordErrorStatus(boolean isVisibleError) {
        assertEquals(isVisibleError, !driver.findElements(passwordErrorText).isEmpty());
    }

    @Step("Проверка текста ошибки сохранения пароля")
    public void checkPasswordErrorText() {
        assertEquals("Некорректный пароль", driver.findElement(passwordErrorText).getText());
    }

    @Step("Проверка ошибки сохранения пароля")
    public void checkPasswordError(boolean isVisibleError) {
        if (isVisibleError) {
            checkPasswordErrorStatus(isVisibleError);
            checkPasswordErrorText();
        } else {
            checkPasswordErrorStatus(isVisibleError);
        }
    }

}
