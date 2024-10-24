package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    public static WebDriver driver = null;

    //Factory method for getting browsers
    public static WebDriver getDriver(String browserName) {
        switch (browserName) {
            case "Chrome":
                if (driver == null) {
                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\digger\\IdeaProjects\\AutomationDemoProject\\src\\main\\resources\\drivers\\chromedriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--disable-search-engine-choice-screen");
                    driver = new ChromeDriver(options);
                }
                break;

            case "Firefox":
                if (driver == null) {
                    System.setProperty("webdriver.gecko.driver", "C:\\Users\\digger\\IdeaProjects\\AutomationDemoProject\\src\\main\\resources\\drivers\\geckodriver.exe");
                    FirefoxOptions options = new FirefoxOptions();
                    options.setLegacy(true);
                    driver = new FirefoxDriver(options);
                }
                break;

            case "Edge":
                if (driver == null) {
                    System.setProperty("webdriver.edge.driver", "C:\\Users\\digger\\IdeaProjects\\AutomationDemoProject\\src\\main\\resources\\drivers\\msedgedriver.exe");
                    driver = new EdgeDriver();
                }
                break;
            default:
                throw new IllegalArgumentException("The browser type " + browserName + " is not defined");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}