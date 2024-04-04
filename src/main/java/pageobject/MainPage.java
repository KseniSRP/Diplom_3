package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage extends BasePage {

    // Локатор для кнопки "Войти в аккаунт"
    private By loginButton = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");

    // Локатор заголовока страницы конструктора бургеров
    private By pageHeaderByText = By.xpath("//h1[contains(text(), 'Соберите бургер')]");

    // Локаторы для разделов соусы/булки/начинки
    private By bunsTab = By.xpath("//span[contains(text(),'Булки')]/ancestor::div[contains(@class, 'tab_tab__')]");
    private By saucesTab = By.xpath("//span[contains(text(),'Соусы')]/ancestor::div[contains(@class, 'tab_tab__')]");
    private By fillingsTab = By.xpath("//span[contains(text(),'Начинки')]/ancestor::div[contains(@class, 'tab_tab__')]");

    // Локаторы для проверки активного состояния вкладок соусы/булки/начинки
    private By activeBunsTab = By.xpath("//span[contains(text(),'Булки')]/ancestor::div[contains(@class, 'tab_tab_type_current__')]");
    private By activeSaucesTab = By.xpath("//span[contains(text(),'Соусы')]/ancestor::div[contains(@class, 'tab_tab_type_current__')]");
    private By activeFillingsTab = By.xpath("//span[contains(text(),'Начинки')]/ancestor::div[contains(@class, 'tab_tab_type_current__')]");
    // Конструктор
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке 'Войти в аккаунт' на главной")
    public void clickLoginButton() {
        WebElement button = waitForElementToBeVisible(loginButton);
        button.click();
    }

    @Step("Проверка наличия заголовка 'Соберите бургер'")
    public void assertHeaderIsVisible() {
        try {
            WebElement header = waitForElementToBeVisible(pageHeaderByText);
            Assert.assertTrue("Заголовок 'Соберите бургер' отсутствует", header.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("Заголовок 'Соберите бургер' не найден");
        }
    }

    @Step("Проверка, что вкладка стала активной")
    public void verifyTabIsActive(By activeTab, String tabName) {
        WebElement activeTabElement = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(activeTab));
        Assert.assertTrue("Вкладка " + tabName + " не стала активной", activeTabElement.isDisplayed());
    }

    @Step("Клик по вкладке 'Булки' и проверка ее активности")
    public void clickBunsTabAndVerify() {
        driver.findElement(bunsTab).click();
        verifyTabIsActive(activeBunsTab, "Булки");
    }

    @Step("Клик по вкладке 'Соусы' и проверка ее активности")
    public void clickSaucesTabAndVerify() {
        driver.findElement(saucesTab).click();
        verifyTabIsActive(activeSaucesTab, "Соусы");
    }

    @Step("Клик по вкладке 'Начинки' и проверка ее активности")
    public void clickFillingsTabAndVerify() {
        driver.findElement(fillingsTab).click();
        verifyTabIsActive(activeFillingsTab, "Начинки");
    }
}
