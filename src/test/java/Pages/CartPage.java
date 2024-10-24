package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "span.title")
    @CacheLookup
    WebElement cartPageTitle;

    @FindBy(css = "button#checkout")
    @CacheLookup
    WebElement checkoutBtn;

    @FindBy(className = "inventory_item_name")
    List<WebElement> cartProductNameList;

    List<String> actualAddedProductNames;
    String cartUrl = "https://www.saucedemo.com/cart.html";

    //Constructor that will be automatically called as soon as the object of the class is created
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyCartPageTitle() {
        Assert.assertEquals("Expected and actual cart page title do not match", "Your Cart", getTextFromElement(cartPageTitle));
    }

    public void verifyCartPageUrl() {
        Assert.assertEquals("Expected and actual shopping cart page do not match", cartUrl, driver.getCurrentUrl());
    }

    public List<String> getActualAddedProductNames() {
        actualAddedProductNames = new ArrayList<>();
        for (WebElement addedProductName : cartProductNameList) {
            actualAddedProductNames.add(String.valueOf(getTextFromElement(addedProductName)));
        }
        return actualAddedProductNames;
    }

    public void clickOnCheckout() {
        Assert.assertTrue("Expected and actual checkout button title do not match", getTextFromElement(checkoutBtn).equalsIgnoreCase("checkout"));
        Assert.assertEquals("Expected and actual checkout button color do not match", "rgba(61, 220, 145, 1)", checkoutBtn.getCssValue("background-color"));
        clickOnElement(checkoutBtn);
    }
}