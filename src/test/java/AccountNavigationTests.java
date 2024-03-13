import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageobject.HeaderPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;

public class AccountNavigationTests extends BaseTest {
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
    @DisplayName("Переход в профиль юзера по кнопке 'Личный кабинет' в шапке сайта")
    @Description("Тест проверяет переход в личный кабинет юзера по клику на кнопку 'Личный кабинет' в шапке сайта")
    public void testNavigateToPersonalAccount () {
        headerPage.clickOnPersonalAccountLink();
        loginPage.enterEmail(testUserResponse.getUser().getEmail());
        loginPage.enterPassword(testUserResponse.getUser().getPassword());
        loginPage.clickLoginButton();
        headerPage.clickOnPersonalAccountLink();
        profilePage.verifyProfilePageElements();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на лого в шапке сайта")
    @Description("Тест на проверяет переход из личного кабинета в конструктор по клику на лого в шапке сайта")
    public void testNavigateFromAccountToConstructor(){
        headerPage.clickOnPersonalAccountLink();
        headerPage.clickOnLogo();
        mainPage.assertHeaderIsVisible();
    }

}
