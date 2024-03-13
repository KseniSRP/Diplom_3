import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import pageobject.HeaderPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;

public class LogoutTest extends BaseTest {


    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private HeaderPage headerPage;

    @Before
    public void setUp() {
        super.setUp();

        // Инициализация страниц
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        headerPage = new HeaderPage(driver);

    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке 'Выход'")
    @Description("Тест проверяет возможность выхода из аккаунта по кнопке 'Выход' на странице профиля: предварительно создаем юзера через API, логинимся в него и после делаем logout")
    public void logoutViaLogoutButton() {
        mainPage.clickLoginButton();
        loginPage.enterEmail(testUserResponse.getUser().getEmail());
        loginPage.enterPassword(testUserResponse.getUser().getPassword());
        loginPage.clickLoginButton();
        headerPage.clickOnPersonalAccountLink();
        profilePage.clickLogoutButton();
        loginPage.isLoginFormVisible();
    }
}

