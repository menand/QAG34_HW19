package config;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.function.Supplier;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;

public class WebDriverProvider implements Supplier<WebDriver> {

    private final WebDriverConfig config;

    public WebDriverProvider() {
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    @Override
    public WebDriver get() {
        if (config.isRemote()) {
            return createRemoteWebDriver();
        } else {
            return createLocalWebDriver();
        }
    }
    private WebDriver createRemoteWebDriver() {
        switch (config.getBrowser()) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setBrowserVersion(config.getBrowserVersion());
                return new RemoteWebDriver(config.getRemoteUrl(), chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBrowserVersion(config.getBrowserVersion());
                return new RemoteWebDriver(config.getRemoteUrl(), firefoxOptions);
            default:
                throw new RuntimeException("Unsupported browser: " + config.getBrowser());
        }
    }
    private WebDriver createLocalWebDriver() {
        switch (config.getBrowser()) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new org.openqa.selenium.chrome.ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new org.openqa.selenium.firefox.FirefoxDriver();
            }
            default -> throw new RuntimeException("Unsupported browser: " + config.getBrowser());
        }
    }
}