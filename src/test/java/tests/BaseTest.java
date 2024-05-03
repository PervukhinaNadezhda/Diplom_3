package tests;

import com.github.javafaker.Faker;
import helper.user.UserMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    WebDriver driver;
    static Faker faker = new Faker();
    private String accessToken;

    @Step("Создание драйвера браузера")
    public void createDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else {
            System.out.println("Неподдерживаемый браузер: " + browser);
        }
    }

    @Step("Создание тестового пользователя")
    public void createUser(String email, String password, String name) {
        accessToken = UserMethods.createUser(email, password, name).then().extract().path("accessToken").toString();
    }

    @Step("Логин в созданного юзера")
    public void loginUser(String email, String password, String name) {
        accessToken = UserMethods.loginUser(email, password, name).then().extract().path("accessToken").toString();
    }

    @Before
    public void setUp() {
        createDriver("chrome");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserMethods.deleteUser(accessToken);
        }
        driver.quit();
    }

}
