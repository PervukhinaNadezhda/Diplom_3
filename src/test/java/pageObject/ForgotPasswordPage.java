package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private final WebDriver driver;
    private final By loginButton = By.className("Auth_link__1fOlj");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}



