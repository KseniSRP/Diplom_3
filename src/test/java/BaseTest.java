import api.UserClient;
import io.restassured.RestAssured;
import model.User;
import model.UserResponse;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.internal.Classes.getClass;



public class BaseTest {
    protected WebDriver driver; // Веб-драйвер для управления браузером
    protected UserResponse testUserResponse; // Объект для хранения данных тестового пользователя и его токена
    protected UserClient userClient; // Клиент API для взаимодействия с пользовательскими данными
    protected boolean createUser = true; // Флаг, указывающий на необходимость создания пользователя перед тестами

    @Before
    public void setUp() {
        /* Для запуска Yandex браузера
        String driverPath = getClass().getClassLoader().getResource("yandexdriver").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site");
        */

        //Для запуска Chrome браузера
         String driverPath = getClass().getClassLoader().getResource("chromedriver").getPath();
         System.setProperty("webdriver.chrome.driver", driverPath);
         ChromeOptions options = new ChromeOptions();
         options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
         driver = new ChromeDriver(options);
         driver.manage().window().maximize();
         driver.get("https://stellarburgers.nomoreparties.site");

        // Установка базового URI для RestAssured
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        // Создание клиента для работы с API пользователей
        userClient = new UserClient();

        if (createUser) {
            // Создание тестового пользователя
            User testUser = new User("TestUser", "testuser" + System.currentTimeMillis() + "@yandex.ru", "password123");
            // Регистрация пользователя через API и сохранение возвращаемого объекта, включая токен
            testUserResponse = userClient.createUser(testUser);
        }
    }

    @After
    public void tearDown() {
        // Если тестовый пользователь был создан, удаляем его через API, используя токен доступа
        if (testUserResponse != null && testUserResponse.getAccessToken() != null) {
            userClient.deleteUser(testUserResponse.getAccessToken());
        }

        // Закрытие браузера и драйвера после выполнения тестов
        if (driver != null) {
            driver.quit();
        }
    }
}
