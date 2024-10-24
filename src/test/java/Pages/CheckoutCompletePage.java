package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BasePage {

    @FindBy(css = "span.title")
    @CacheLookup
    WebElement checkoutCompleteTitle;

    @FindBy(className = "complete-header")
    WebElement completeMsg;

    @FindBy(className = "complete-text")
    WebElement completeTextMsg;

    String checkoutCompleteUrl = "https://www.saucedemo.com/checkout-complete.html";
    String expectedCompleteMessage = "Thank you for your order!";
    String actualCompleteMessage;
    String actualCompleteTextMessage;

    //Constructor that will be automatically called as soon as the object of the class is created
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyCheckoutCompleteTitle() {
        Assert.assertEquals("Expected and actual checkout complete page title do not match", "Checkout: Complete!", getTextFromElement(checkoutCompleteTitle));
    }

    public void verifyCheckoutCompleteUrl() {
        Assert.assertEquals("Expected and actual checkout complete page do not match", checkoutCompleteUrl, driver.getCurrentUrl());
    }

    public void verifyOrder() {
        actualCompleteMessage = getTextFromElement(completeMsg);
        actualCompleteTextMessage = getTextFromElement(completeTextMsg);
        Assert.assertEquals("Order is not successfully completed", expectedCompleteMessage, actualCompleteMessage);
        Assert.assertTrue("Order is not successfully dispatched", actualCompleteTextMessage.contains("order has been dispatched"));
    }
}