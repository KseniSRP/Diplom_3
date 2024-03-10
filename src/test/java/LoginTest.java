
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import pageobject.*;

public class LoginTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;
    private HeaderPage headerPage;
    @Before
    public void setUp() {
        super.setUp();
        // Инициализация страниц
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        headerPage = new HeaderPage(driver);
    }
    @Test
    @DisplayName("Успешный вход через кнопку 'Войти в аккаунт'")
    @Description("Тест проверяет возможность входа в систему через кнопку 'Войти в аккаунт' на главной странице.")
    public void loginViaLoginButton() {
        mainPage.clickLoginButton();
        loginPage.isLoginFormVisible();
        loginPage.enterEmail(testUserResponse.getUser().getEmail());
        loginPage.enterPassword(testUserResponse.getUser().getPassword());
        loginPage.clickLoginButton();
        headerPage.clickOnPersonalAccountLink();
        profilePage.verifyProfilePageElements();
    }

    @Test
    @DisplayName("Успешный вход через кнопку 'Личный кабинет'")
    @Description("Тест проверяет возможность входа в систему через кнопку 'Личный кабинет' в шапке на главной странице в.")
    public void loginViaPersonalAccountLink() {
        headerPage.clickOnPersonalAccountLink();
        loginPage.isLoginFormVisible();
        loginPage.enterEmail(testUserResponse.getUser().getEmail());
        loginPage.enterPassword(testUserResponse.getUser().getPassword());
        loginPage.clickLoginButton();
        headerPage.clickOnPersonalAccountLink();
        profilePage.verifyProfilePageElements();
    }

    @Test
    @DisplayName("Успешный вход через ссылку 'Войти' в форме регистрации")
    @Description("Тест проверяет возможность входа в систему через ссылку 'Войти' в форме регистрации")
    public void loginViaRegisterFormButton() {
        headerPage.clickOnPersonalAccountLink();
        loginPage.scrollToRegisterLinkAndClick();
        registrationPage.clickLoginLinkOnRegistrationForm();
        loginPage.isLoginFormVisible();
        loginPage.enterEmail(testUserResponse.getUser().getEmail());
        loginPage.enterPassword(testUserResponse.getUser().getPassword());
        loginPage.clickLoginButton();
        headerPage.clickOnPersonalAccountLink();
        profilePage.verifyProfilePageElements();
    }

    @Test
    @DisplayName("Успешный вход через ссылку 'Войти' в форме восстановления пароля")
    @Description("Тест проверяет возможность входа в систему через ссылку 'Войти' в форме восстановления пароля.")
    public void loginViaPasswordRecoveryFormButton() {
            headerPage.clickOnPersonalAccountLink();
            loginPage.clickForgotPasswordLink();
            forgotPasswordPage.clickLoginLinkOnForgotPasswordForm();
            loginPage.isLoginFormVisible();
            loginPage.enterEmail(testUserResponse.getUser().getEmail());
            loginPage.enterPassword(testUserResponse.getUser().getPassword());
            loginPage.clickLoginButton();
            headerPage.clickOnPersonalAccountLink();
            profilePage.verifyProfilePageElements();
        }

}