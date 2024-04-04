package pageobject;



import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class ForgotPasswordPage extends BasePage {

    // Локатор ссылки 'Войти' на странице восстановления пароля
    private By loginLink = By.cssSelector("a.Auth_link__1fOlj[href='/login']");

    // Конструктор
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по ссылке 'Войти' на странице восстановления пароля")
    public void clickLoginLinkOnForgotPasswordForm() {
        WebElement button = waitForElementToBeVisible(loginLink);
        scrollToElement(button);
        button.click();
    }
}
