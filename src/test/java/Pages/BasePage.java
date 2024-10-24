package Pages;

import Helpers.Waits;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BasePage {
    WebDriver driver;
    Select select;
    List<WebElement> selectedOptionsList;

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Custom page method to get text of an element
     *
     * @param element Method parameter of WebElement type
     * @return It returns the visible text of an element as a String
     */
    public String getTextFromElement(WebElement element) {
        Waits.waitVisibilityOfElement(driver, 10, element);
        return element.getText();
    }

    /**
     * Custom page method to set value to element
     *
     * @param element The first method parameter of WebElement type
     * @param text    The second method parameter of String type
     */
    public void setValueToElement(WebElement element, String text) {
        Waits.waitVisibilityOfElement(driver, 10, element);
        element.sendKeys(text);
        Assert.assertTrue("Expected and actual input value do not match", getAttributeFromElement(element, "value").contains(text));
    }

    /**
     * Custom page method to get value from element
     *
     * @param element Method parameter of WebElement type
     * @param attr    The second method parameter of String type which is a specified attribute on an element
     * @return It returns the value of a certain attribute of an element
     */
    public String getAttributeFromElement(WebElement element, String attr) {
        Waits.waitVisibilityOfElement(driver, 10, element);
        return element.getAttribute(attr);
    }

    /**
     * Custom page method to erase the contents from text boxes
     *
     * @param element Method parameter of WebElement type
     */
    public void clearElement(WebElement element) {
        Waits.waitElementToBeClickable(driver, 10, element);
        element.clear();
        Assert.assertTrue(getAttributeFromElement(element, "value").isBlank());
    }

    /**
     * Custom page method to click on element
     *
     * @param element Method parameter of WebElement type
     */
    public void clickOnElement(WebElement element) {
        Waits.waitElementToBeClickable(driver, 10, element);
        element.click();
    }

    /**
     * Custom page method to select one of the options in a drop-down box or an option among multiple selection boxes using the text over the option.
     *
     * @param element The first method parameter of WebElement type
     * @param text    The second method parameter of String type which is one of the values of Select element
     */
    public void selectElementFromDropDownByVisibleText(WebElement element, String text) {
        Waits.waitElementToBeClickable(driver, 10, element);
        select = new Select(element);
        Assert.assertFalse("Option list is empty", select.getOptions().isEmpty());
        select.selectByVisibleText(text);
    }

    /**
     * Custom page method to get text of selected element in a drop-down box
     *
     * @param element Method parameter of WebElement type
     * @return It returns the visible text of an element as a String
     */
    public String getSelectedElementsFromDropDown(WebElement element) {
        String options = "";
        Waits.waitVisibilityOfElement(driver, 10, element);
        select = new Select(element);
        selectedOptionsList = select.getAllSelectedOptions();
        for (WebElement elem : selectedOptionsList) {
            options = getTextFromElement(elem);
        }
        return options;
    }

    /**
     * Custom page method to verify whether the specified select element support selecting multiple options at the same time
     *
     * @param element Method parameter of WebElement type
     * @return It returns true when the specified select element support selecting multiple options else it will return false
     */
    public boolean isElementMultiple(WebElement element) {
        select = new Select(element);
        return select.isMultiple();
    }
}