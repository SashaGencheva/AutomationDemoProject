package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInfoPage extends BasePage {

    @FindBy(css = "span.title")
    @CacheLookup
    WebElement checkoutInfoTitle;

    @FindBy(css = "input#first-name")
    @CacheLookup
    WebElement firstNameField;

    @FindBy(xpath = "//input[@name = 'lastName']")
    @CacheLookup
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    @CacheLookup
    WebElement zipCodeField;

    @FindBy(css = "input[type = 'submit']")
    @CacheLookup
    WebElement continueBtn;

    String checkoutInfoUrl = "https://www.saucedemo.com/checkout-step-one.html";

    //Constructor that will be automatically called as soon as the object of the class is created
    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyCheckoutInfoTitle() {
        Assert.assertEquals("Expected and actual checkout information page title do not match", "Checkout: Your Information", getTextFromElement(checkoutInfoTitle));
    }

    public void verifyCheckoutInfoUrl() {
        Assert.assertEquals("Expected and actual checkout information page do not match", checkoutInfoUrl, driver.getCurrentUrl());
    }

    public void setFirstName(String firstName) {
        Assert.assertTrue("Expected and actual firstname field placeholder do not match", getAttributeFromElement(firstNameField, "placeholder").contains("First"));
        clearElement(firstNameField);
        setValueToElement(firstNameField, firstName);
    }

    public void setLastName(String lastName) {
        Assert.assertTrue("Expected and actual lastname field placeholder do not match", getAttributeFromElement(lastNameField, "placeholder").contains("Last"));
        clearElement(lastNameField);
        setValueToElement(lastNameField, lastName);
    }

    public void setZipCode(String zipCode) {
        Assert.assertTrue("Expected and actual zipcode field placeholder do not match", getAttributeFromElement(zipCodeField, "placeholder").contains("Zip"));
        clearElement(zipCodeField);
        setValueToElement(zipCodeField, zipCode);
    }

    public void clickOnContinue() {
        Assert.assertTrue("Expected and actual continue button title do not match", getAttributeFromElement(continueBtn, "value").equalsIgnoreCase("continue"));
        Assert.assertEquals("Expected and actual continue button color do not match", "rgba(61, 220, 145, 1)", continueBtn.getCssValue("background-color"));
        clickOnElement(continueBtn);
    }
}