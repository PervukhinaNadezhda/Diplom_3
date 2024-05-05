package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

    public static void waitFor(WebDriver driver, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

}
