package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    @FindBy(css = "span.title")
    @CacheLookup
    WebElement checkoutOverviewTitle;

    @FindBy(className = "inventory_item_name")
    List<WebElement> checkoutOverviewProductNameList;

    @FindBy(css = "div.inventory_item_price")
    List<WebElement> checkoutOverviewProductPriceList;

    @FindBy(css = "div[data-test='payment-info-label']")
    @CacheLookup
    WebElement paymentInfoLabel;

    @FindBy(xpath = "//div[@data-test='shipping-info-label']")
    @CacheLookup
    WebElement shippingInfoLabel;

    @FindBy(css = "div[data-test='total-info-label']")
    @CacheLookup
    WebElement priceInfoLabel;

    @FindBy(xpath = "//div[@data-test='payment-info-value']")
    WebElement paymentInfoField;

    @FindBy(css = "div[data-test='shipping-info-value']")
    WebElement shippingInfoField;

    @FindBy(xpath = "//div[@data-test='subtotal-label']")
    WebElement itemTotalField;

    @FindBy(css = "div[data-test='tax-label']")
    WebElement taxField;

    @FindBy(xpath = "//div[@data-test='total-label']")
    WebElement priceTotalField;

    @FindBy(id = "finish")
    @CacheLookup
    WebElement finishBtn;

    List<String> actualCheckoutOverviewProductNames;
    List<Double> actualCheckoutOverviewProductPrices;
    double expectedProductPricesSum = 0.00;
    double actualProductPricesSum;
    double tax;
    double expectedPriceTotal = 0.00;
    double actualPriceTotal;
    String checkoutOverviewUrl = "https://www.saucedemo.com/checkout-step-two.html";

    //Constructor that will be automatically called as soon as the object of the class is created
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyCheckoutOverviewTitle() {
        Assert.assertEquals("Expected and actual checkout overview page title do not match", "Checkout: Overview", getTextFromElement(checkoutOverviewTitle));
    }

    public void verifyCheckoutOverviewUrl() {
        Assert.assertEquals("Expected and actual checkout overview page do not match", checkoutOverviewUrl, driver.getCurrentUrl());
    }

    public List<String> getActualCheckoutOverviewProductNames() {
        actualCheckoutOverviewProductNames = new ArrayList<>();
        for (WebElement productName : checkoutOverviewProductNameList) {
            actualCheckoutOverviewProductNames.add(String.valueOf(getTextFromElement(productName)));
        }
        return actualCheckoutOverviewProductNames;
    }

    public double calculateExpectedProductPricesSum() {
        actualCheckoutOverviewProductPrices = new ArrayList<>();
        for (WebElement productPrice : checkoutOverviewProductPriceList) {
            actualCheckoutOverviewProductPrices.add(Double.valueOf(getTextFromElement(productPrice).replace("$", "")));
        }
        for (double itemPrice : actualCheckoutOverviewProductPrices) {
            expectedProductPricesSum += itemPrice;
        }
        return expectedProductPricesSum;
    }

    public void verifyPaymentInfo() {
        Assert.assertTrue("Expected and actual payment information label do not match", getTextFromElement(paymentInfoLabel).contains("Payment"));
        Assert.assertTrue("Expected and actual payment card do not match", getTextFromElement(paymentInfoField).contains("Card #31337"));
    }

    public void verifyShippingInfo() {
        Assert.assertTrue("Expected and actual shipping information label do not match", getTextFromElement(shippingInfoLabel).equalsIgnoreCase("shipping information:"));
        Assert.assertTrue("Expected and actual shipping company do not match", getTextFromElement(shippingInfoField).equalsIgnoreCase("Free Pony Express Delivery!"));
    }

    public void verifyProductPriceTotal() {
        actualProductPricesSum = Double.parseDouble(getTextFromElement(itemTotalField).replace("Item total: $", ""));
        expectedProductPricesSum = calculateExpectedProductPricesSum();
        tax = Double.parseDouble(getTextFromElement(taxField).replace("Tax: $", ""));
        expectedPriceTotal = expectedProductPricesSum + tax;
        actualPriceTotal = Double.parseDouble(getTextFromElement(priceTotalField).replace("Total: $", ""));

        Assert.assertEquals("Expected and actual price total label do not match", "Price Total", getTextFromElement(priceInfoLabel));
        Assert.assertEquals("Expected and actual product prices sum do not match", expectedProductPricesSum, actualProductPricesSum, 0.00);
        Assert.assertEquals("Expected and actual price total do not match", expectedPriceTotal, actualPriceTotal, 0.00);
    }

    public void clickOnFinish() {
        Assert.assertTrue("Expected and actual finish button title do not match", getTextFromElement(finishBtn).equalsIgnoreCase("finish"));
        Assert.assertEquals("Expected and actual finish button color do not match", "rgba(61, 220, 145, 1)", finishBtn.getCssValue("background-color"));
        clickOnElement(finishBtn);
    }
}