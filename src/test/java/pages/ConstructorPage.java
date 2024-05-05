package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helper.ConstantURL.CONSTRUCTOR_PAGE;
import static org.junit.Assert.assertEquals;

public class ConstructorPage {

    private final WebDriver driver;
    private final By constructorLabel = By.xpath(".//*[text() = 'Соберите бургер']");
    private final By sousesButton = By.xpath(".//span[text() = 'Соусы']");
    private final By fillingsButton = By.xpath(".//span[text() = 'Начинки']");
    private final By bunsButton = By.xpath(".//span[text() = 'Булки']");
    private final By selectedSection = By.xpath(".//div[contains(@class, 'current')]");
    private final By loginAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//*[text() = 'Личный Кабинет']");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
    }

    @Step("Нажать кнопку 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажать кнопку 'Соусы'")
    public void clickSousesButton() {
        driver.findElement(sousesButton).click();
    }

    @Step("Нажать кнопку 'Начинки'")
    public void clickFillingsButton() {
        driver.findElement(fillingsButton).click();
    }

    @Step("Нажать кнопку 'Булки'")
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    @Step("Ожидание прогрузки страницы конструктора.")
    public void waitForLoadConstructorPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(constructorLabel));
    }

    @Step("Проверка открытия страницы конструктора.")
    public void checkConstructorPageUrl() {
        assertEquals(CONSTRUCTOR_PAGE, driver.getCurrentUrl());
    }

    @Step("Проверка текста выбранной секции 'Соусы'.")
    public void checkSelectedSectionSousesText() {
        assertEquals("Соусы", driver.findElement(selectedSection).getText());
    }

    @Step("Проверка текста выбранной секции 'Начинки'.")
    public void checkSelectedSectionFillingsText() {
        assertEquals("Начинки", driver.findElement(selectedSection).getText());
    }

    @Step("Проверка текста выбранной секции 'Булки'.")
    public void checkSelectedSectionBunsText() {
        assertEquals("Булки", driver.findElement(selectedSection).getText());
    }

}
