package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage {

    // Локатор для текста с информацией о персональных данных
    private By personalDataText = By.cssSelector("p.Account_text__fZAIn.text.text_type_main-default");

    // Локатор для кнопки "Выход"
    private By logoutButton = By.cssSelector("button.Account_button__14Yp3.text.text_type_main-medium.text_color_inactive");

    // Конструктор
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Проверяет, виден ли текст с информацией о персональных данных на странице.
     * @return true если текст отображается, иначе false.
     */

    public boolean isPersonalDataTextVisible() {
        try {
            WebElement textElement = waitForElementToBeVisible(personalDataText);
            return textElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет, видна ли кнопка "Выход" на странице.
     * @return true если кнопка отображается, иначе false.
     */
    public boolean isLogoutButtonVisible() {
        try {
            WebElement buttonElement = waitForElementToBeVisible(logoutButton);
            return buttonElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Нажимаем на кнопку 'Выход' в профиле юзера")
    public void clickLogoutButton() {
        WebElement logoutButtonElement = waitForElementToBeVisible(logoutButton);
        Assert.assertTrue("Кнопка 'Выход' не видима перед кликом", logoutButtonElement.isDisplayed());
        logoutButtonElement.click();
        // После клика проверяем, что мы действительно вышли из профиля (например, проверяем, что кнопка "Войти" стала видима)
    }
    @Step("Проверка видимости текста о персональных данных и кнопки 'Выход' на странице профиля")
    public void verifyProfilePageElements() {
        // Удостоверимся, что текст о персональных данных виден
        boolean isPersonalDataTextVisible = isPersonalDataTextVisible();
        Assert.assertTrue("Текст о персональных данных не отображается", isPersonalDataTextVisible);

        // Удостоверимся, что кнопка 'Выход' видна
        boolean isLogoutButtonVisible = isLogoutButtonVisible();
        Assert.assertTrue("Кнопка 'Выход' не отображается", isLogoutButtonVisible);
    }

    }
