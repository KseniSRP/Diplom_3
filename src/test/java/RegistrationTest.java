import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import pageobject.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest extends BaseTest {

    private RegistrationPage registrationPage;
    private ProfilePage profilePage;
    private MainPage mainPage;
    private LoginPage loginPage;
    private String name;
    private String email;
    private String goodPassword;
    private String wrongPassword;
    private HeaderPage headerPage;


    @Before
    @Override
    public void setUp() {
        createUser = false; // Не создавать пользователя для этого теста
        super.setUp(); // Инициализация в BaseTest
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        profilePage = new ProfilePage(driver);
        headerPage = new HeaderPage(driver);
        name = "registrationUser";
        goodPassword = "password123";
        wrongPassword = "123";
        String uniqueSuffix = Long.toString(System.currentTimeMillis());
        email = "registrationUser" + uniqueSuffix + "@yandex.ru";
        mainPage.clickLoginButton();
        loginPage.scrollToRegisterLinkAndClick();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации пользователя с валидными данными.")
    public void successfulRegistrationTest() {
        registrationPage.enterName(name);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(goodPassword);
        registrationPage.clickRegisterButton();
        // Проверка, что можно авторизоваться созданным юзером
        loginPage.enterEmail(email);
        loginPage.enterPassword(goodPassword);
        loginPage.clickLoginButton();
        headerPage.clickOnPersonalAccountLink();
        // Проверки на странице профиля что мы авторизовались
        profilePage.verifyProfilePageElements();
    }

    @Test
    @DisplayName("Ошибка при некорректном пароле")
    @Description("Проверка отображения ошибки при использовании некорректного пароля при регистрации.")
    public void incorrectPasswordErrorTest() {
        registrationPage.enterName(name);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(wrongPassword);
        registrationPage.clickRegisterButton();
        // Проверка наличия сообщения об ошибке
        registrationPage.verifyPasswordErrorMessage();
    }
}

