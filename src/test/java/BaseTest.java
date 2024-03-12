import api.UserClient;
import io.restassured.RestAssured;
import model.User;
import model.UserResponse;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.internal.Classes.getClass;



public class BaseTest {
    protected WebDriver driver; // Веб-драйвер для управления браузером
    protected UserResponse testUserResponse; // Объект для хранения данных тестового пользователя и его токена
    protected UserClient userClient; // Клиент API для взаимодействия с пользовательскими данными
    protected boolean createUser = true; // Флаг, указывающий на необходимость создания пользователя перед тестами

    @Before
    public void setUp() {

         final String BASE_URL = "https://stellarburgers.nomoreparties.site";
        // Установка базового URI для RestAssured
         RestAssured.baseURI = BASE_URL;

        // Дефолтное значение для браузера
        String browserName = System.getProperty("browser", "chrome");

        // Получение пути к драйверу в зависимости от выбранного браузера при запуске тестов
        String driverPath = "";
        if (browserName.equalsIgnoreCase("yandex")) {
            driverPath = "/yandexdriver"; // относительный путь к Yandex драйверу
        } else { // Для Chrome и других браузеров
            driverPath = "/chromedriver"; // относительный путь к Chrome драйверу
        }

        try {
            // Загрузка пути к драйверу из ресурсов
            URL driverUrl = BaseTest.class.getResource(driverPath);
            if (driverUrl == null) {
                throw new RuntimeException("Не удалось найти драйвер в папке ресурсов");
            }
            String driverAbsolutePath = Paths.get(driverUrl.toURI()).toString();

            // Установка системной переменной для указания пути к драйверу
            System.setProperty("webdriver.chrome.driver", driverAbsolutePath);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Ошибка при обработке URI драйвера", e);
        }

        // Настройка и запуск WebDriver
        ChromeOptions options = new ChromeOptions();
        if (browserName.equalsIgnoreCase("yandex")) {
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        }
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(BASE_URL);

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
