package Helpers;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver("Chrome");
    }

    @After
    public void tearDown() {
        BrowserFactory.closeDriver();
    }
}