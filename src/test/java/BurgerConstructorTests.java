import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;

public class BurgerConstructorTests extends BaseTest {
    private MainPage mainPage;

    @Before
    @Override
    public void setUp() {
        createUser = false; // Не создавать пользователя для этого теста
        super.setUp();
        // Инициализация страниц
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Тест на проверку раздела конструктора 'Соусы'")
    @Description("В тесте проверяется, что при клике раздела 'Соусы' в контсрукторе отображается соответсвующий раздел")
    public void saucesSectionIsCorrect() {
     mainPage.clickSaucesTabAndVerify();
    }

    @Test
    @DisplayName("Тест на проверку раздела конструктора 'Начинки'")
    @Description("В тесте проверяется, что при клике раздела 'Начинки' в контсрукторе отображается соответсвующий раздел")
    public void fillingsSectionIsCorrect() {
        mainPage.clickFillingsTabAndVerify();
    }

    @Test
    @DisplayName("Тест на проверку раздела конструктора 'Булки'")
    @Description("В тесте проверяется, что при клике раздела 'Булки' в контсрукторе отображается соответсвующий раздел. Для смены раздела 'Булки' вначале делаем переключение на соусы")
    public void bunsSectionIsCorrect() {
        mainPage.clickFillingsTabAndVerify(); //клик не на булки так как они выбраны по умолчанию и нам нужно проверить именно действие по клику когда булки не активны
        mainPage.clickBunsTabAndVerify();
    }

}
