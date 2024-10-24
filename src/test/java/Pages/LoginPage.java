package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    @CacheLookup
    WebElement userNameField;

    @FindBy(css = "input#password")
    @CacheLookup
    WebElement passwordField;

    @FindBy(name = "login-button")
    @CacheLookup
    WebElement loginBtn;

    @FindBy(className = "error-message-container")
    WebElement errorMsg;

    String errorMessage;

    //Constructor that will be automatically called as soon as the object of the class is created
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Page method for setting username
    public void setUserName(String userName) {
        Assert.assertTrue("Expected and actual username field placeholder do not match", getAttributeFromElement(userNameField, "placeholder").contains("User"));
        clearElement(userNameField);
        setValueToElement(userNameField, userName);
    }

    //Page method for setting password
    public void setPassword(String password) {
        Assert.assertTrue("Expected and actual password field placeholder do not match", getAttributeFromElement(passwordField, "placeholder").contains("Pass"));
        clearElement(passwordField);
        setValueToElement(passwordField, password);
    }

    //Page method for clicking on Login button
    public void clickOnLogin() {
        Assert.assertTrue("Expected and actual login button title do not match", getAttributeFromElement(loginBtn, "value").equalsIgnoreCase("login"));
        Assert.assertEquals("Expected and actual login button color do not match", "rgba(61, 220, 145, 1)", loginBtn.getCssValue("background-color"));
        clickOnElement(loginBtn);
    }

    //Page method for getting error message
    public String getErrorMessage() {
        errorMessage = getTextFromElement(errorMsg);
        Assert.assertEquals("Expected and actual error message color do not match", "rgba(226, 35, 26, 1)", errorMsg.getCssValue("background-color"));
        return errorMessage;
    }
}