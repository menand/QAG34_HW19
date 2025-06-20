package tests;

import config.WebDriverConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleWebTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(SimpleWebTest.class);

    @Test
    void githubTitleTest() {
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);
        if (config.isRemote()) {
            SimpleWebTest.log.info("Запуск в Selenoid");
            ChromeOptions options = new ChromeOptions();
            options.setBrowserVersion(config.getBrowserVersion());
            driver = new RemoteWebDriver(config.getRemoteUrl(), options);
        } else {
            SimpleWebTest.log.info("Локальный запуск");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.get("https://github.com");
        assertThat(driver.getTitle()).contains("GitHub");
    }
}