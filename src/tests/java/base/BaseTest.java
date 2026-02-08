package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;
    protected ChecoutInformationPage checoutInformationPage;
    protected ChecoutOverviewPage checoutOverviewPage;
    protected CheckoutCompletePage checkoutCompletePage;



    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        //Используйте режим гостя или инкогнито для максимальной изоляции, что бы после
        //выполнения первого теста не требовало сменить пароль

        options.addArguments("--guest");

        // Основные настройки для отключения проверки утечки паролей
        options.addArguments("--disable-blink-features=PasswordLeakDetection");
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--password-store=basic");

        // Отключаем инфобар с предложением сохранить/сменить пароль
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);

        // Дополнительные настройки для стабильности тестов
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        prefs.put("enable-automation", false);

        options.setExperimentalOption("prefs", prefs);

        // Отключаем автоматизационные флаги
        options.setExperimentalOption("excludeSwitches",
                new String[]{"enable-automation", "enable-logging"});
        options.setExperimentalOption("useAutomationExtension", false);

        // Инициализируем драйвер с настройками и так же подклчюаем классы
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checoutInformationPage = new ChecoutInformationPage(driver);
        checoutOverviewPage = new ChecoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

        driver.get("https://www.saucedemo.com/");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}