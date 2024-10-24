package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    public static void waitVisibilityOfElement(WebDriver driver, long timeout, WebElement element) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitElementToBeClickable(WebDriver driver, long timeout, WebElement element) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
    }
}