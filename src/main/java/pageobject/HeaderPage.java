package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderPage extends BasePage {

    // Локатор для логотипа в шапке сайта
    private By headerLogoLink = By.cssSelector("div.AppHeader_header__logo__2D0X2 a");

    // Локатор для кнопки 'Личный кабинет' в шапке сайта
    private By personalAccountLink = By.xpath("//p[contains(text(),'Личный Кабинет')]");

    // Конструктор
    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по лого в шапке сайта")
    public void clickOnLogo() {
        WebElement logo = waitForElementToBeVisible(headerLogoLink);
        logo.click();
    }

    @Step("Клик по линку 'Личный Кабинет' в шапке сайта")
    public void clickOnPersonalAccountLink() {
        WebElement link = waitForElementToBeVisible(personalAccountLink);
        link.click();
    }
}
