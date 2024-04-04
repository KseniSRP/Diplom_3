package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    // Локатор для ссылки 'Зарегистрироваться'
    private By registerLink = By.cssSelector("a.Auth_link__1fOlj");

    // Локатор для ссылки 'Восстановить пароль'
    private By forgotPasswordLink = By.xpath("//a[@class='Auth_link__1fOlj' and @href='/forgot-password']");

    //Локатор для поля ввода email
    private By emailInput = By.xpath("//input[@name='name']");

    //Локатор для ввода пароля
    private By passwordInput = By.xpath("//input[@name='Пароль']");

    //Локатор для кнопки 'Войти'
    private By loginButton = By.xpath("//button[contains(@class, 'button_button_type_primary') and contains(@class, 'button_button_size_medium')]");

    // Конструктор
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Метод для скролла к ссылке "Зарегистрироваться" и клика по ней
    public void scrollToRegisterLinkAndClick() {
        WebElement link = waitForElementToBeVisible(registerLink);
        scrollToElement(link);
        link.click();
    }

    @Step("Проверяем, видна ли форма логина")
    public boolean isLoginFormVisible() {
        return isElementVisible(emailInput) && isElementVisible(passwordInput) && isElementVisible(loginButton);
    }
    private boolean isElementVisible(By locator) {
        try {
            return waitForElementToBeVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ввод email в поле ввода")
    public void enterEmail(String email) {
        WebElement emailField = waitForElementToBeVisible(emailInput);
        emailField.sendKeys(email);
    }

    @Step("Ввод пароля в поле ввода")
    public void enterPassword(String password) {
        WebElement passwordField = waitForElementToBeVisible(passwordInput);
        passwordField.sendKeys(password);
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        WebElement button = waitForElementToBeVisible(loginButton);
        button.click();
    }

    @Step("Клик на ссылку 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        WebElement link = waitForElementToBeVisible(forgotPasswordLink);
        scrollToElement(link);
        link.click();
    }

}
