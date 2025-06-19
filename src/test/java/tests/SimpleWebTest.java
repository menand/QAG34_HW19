package tests;

import config.WebDriverConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleWebTest extends TestBase {

    @Test
    void githubTitleTest() {
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);
        WebDriver driver;
        if (config.isRemote()) {
            System.out.println("Запуск в Selenoid");
            ChromeOptions options = new ChromeOptions();
            options.setBrowserVersion(config.getBrowserVersion());
            driver = new RemoteWebDriver(config.getRemoteUrl(), options);
        } else {
            System.out.println("Локальный запуск");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.get("https://github.com");
        assertThat(driver.getTitle()).contains("GitHub");
    }
}