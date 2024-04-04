package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePage {

    // Локатор для поля ввода имени
    private By nameInput = By.xpath(".//fieldset[1]/div/div/input[@name='name']");

    // Локатор для поля ввода email
    private By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");

    // Локатор для поля ввода пароля
    private By passwordInput = By.xpath("//input[@name='Пароль']");

    // Локатор кнопки зарегистрироваться
    private By registerButton = By.cssSelector("button.button_button__33qZ0");

    // Локатор ошибки про неверный пароль
    private By passwordError = By.cssSelector("p.input__error.text_type_main-default");

    // Локатор ссылки 'Войти' на форме регистрации
    private By loginLinkOnRegistrationForm = By.cssSelector("a.Auth_link__1fOlj[href='/login']");
    // Конструктор класса
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Метод для ввода имени
    public void enterName(String name) {
        WebElement nameField = waitForElementToBeVisible(nameInput);
        nameField.clear();
        nameField.sendKeys(name);
    }

    // Метод для ввода email
    public void enterEmail(String email) {
        WebElement emailField = waitForElementToBeVisible(emailInput);
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Метод для ввода пароля
    public void enterPassword(String password) {
        WebElement passwordField = waitForElementToBeVisible(passwordInput);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Step("Клик по кнопке регистрации")
    public void clickRegisterButton() {
        WebElement button = waitForElementToBeVisible(registerButton);
        scrollToElement(button);
        button.click();
    }


    @Step("Клик по ссылке 'Войти' на странице регистрации")
    public void clickLoginLinkOnRegistrationForm() {
        WebElement button = waitForElementToBeVisible(loginLinkOnRegistrationForm);
        scrollToElement(button);
        button.click();
    }

    @Step("Проверки отображения сообщения об ошибке при некорректном пароле")
    public boolean isPasswordErrorDisplayed() {
        try {
            WebElement errorElement = waitForElementToBeVisible(passwordError);
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка наличия сообщения об ошибке для некорректного пароля")
    public void verifyPasswordErrorMessage() {
        boolean isErrorDisplayed = isPasswordErrorDisplayed();
        Assert.assertTrue("Сообщение об ошибке для некорректного пароля должно быть отображено", isErrorDisplayed);
    }

}
