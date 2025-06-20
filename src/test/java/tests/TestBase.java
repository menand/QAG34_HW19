package tests;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;


public abstract class TestBase {

    protected WebDriver driver;

    @AfterEach
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
