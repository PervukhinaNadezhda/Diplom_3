package tests;


import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.RegistrationPage;

import static helper.ConstantURL.REGISTRATION_PAGE;

@RunWith(Parameterized.class)
public class RegistrationPasswordTests extends BaseTest {
    private final String password;
    private final boolean isVisibleError;

    public RegistrationPasswordTests(String password, boolean isVisibleError) {
        this.password = password;
        this.isVisibleError = isVisibleError;
    }

    @Parameterized.Parameters(name = "[{index}]: check error test for password:   {0}")
    public static Object[][] setPassword() {
        return new Object[][]{
                {"", true},
                {"q", true},
                {"qwert", true},
                {"qwerty", false},
                {"qwertyu", false},
                {"qwertqwertqwertqwertqwertqwertqwertqwertqwertqwert", false},
                {"123456", false},
                {"      ", false},
                {",.?!&@", false},
        };
    }

    @Test
    @Description("Проверка ошибки для некорректного пароля на странице регистрации нового пользователя: " +
            "Устанавливается значение пароля. " +
            "Нажатие на кнопку 'Зарегистрироваться'. " +
            "Проверяется наличие или отсутствие ошибки.")
    public void registrationPasswordTest() {
        driver.get(REGISTRATION_PAGE);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setPassword(password);
        registrationPage.clickRegistrationButton();
        registrationPage.checkPasswordError(isVisibleError);
    }
}

