package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helper.ConstantURL.PROFILE_PAGE;
import static org.junit.Assert.assertEquals;

public class ProfilePage {

    private final WebDriver driver;
    private final By profileLabel = By.xpath(".//*[text() = 'Профиль']");
    private final By logoutButton = By.xpath(".//button[text() = 'Выход']");
    private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание прогрузки страницы профиля")
    public void waitForLoadProfilePage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(profileLabel));
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Нажать кнопку логотипа")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    @Step("Нажать кнопку 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Проверка открытия страницы профиля")
    public void checkProfilePageUrl() {
        assertEquals(PROFILE_PAGE, driver.getCurrentUrl());
    }

}
