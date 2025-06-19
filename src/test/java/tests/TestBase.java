package tests;

import config.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


public abstract class TestBase {

    protected WebDriver driver;

    @BeforeEach
    public void setUpDriver() {
        driver = new WebDriverProvider().get();
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
