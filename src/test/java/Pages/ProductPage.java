package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(css = "span.title")
    @CacheLookup
    WebElement productPageTitle;

    @FindBy(css = "a.shopping_cart_link")
    @CacheLookup
    WebElement shoppingCartLink;

    @FindBy(css = "span.shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(css = "select.product_sort_container")
    WebElement productSorter;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNameList;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productPriceList;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement backpackAddToCartBtn;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement backpackRemoveBtn;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement bikeLightAddToCartBtn;

    @FindBy(xpath = "//button[@name = 'remove-sauce-labs-bike-light']")
    WebElement bikeLightRemoveBtn;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    WebElement boltTShirtAddToCartBtn;

    @FindBy(css = "button#remove-sauce-labs-bolt-t-shirt")
    WebElement boltTShirtRemoveBtn;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    WebElement jacketAddToCartBtn;

    @FindBy(xpath = "//button[@id = 'remove-sauce-labs-fleece-jacket']")
    WebElement jacketRemoveBtn;

    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    WebElement onesieAddToCartBtn;

    @FindBy(css = "button[name = 'remove-sauce-labs-onesie']")
    WebElement onesieRemoveBtn;

    @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    WebElement redTShirtAddToCartBtn;

    @FindBy(xpath = "//button[contains(@id, 'red')][text() = 'Remove']")
    WebElement redTShirtRemoveBtn;

    @FindBy(css = "#item_4_title_link > div")
    WebElement backpackName;

    @FindBy(xpath = "//*[@id='item_0_title_link']/div")
    WebElement bikeLightName;

    @FindBy(css = "#item_1_title_link > div")
    WebElement boltTShirtName;

    @FindBy(xpath = "//*[@id='item_5_title_link']/div")
    WebElement jacketName;

    @FindBy(css = "#item_2_title_link > div")
    WebElement onesieName;

    @FindBy(xpath = "//*[@id='item_3_title_link']/div")
    WebElement redTShirtName;

    int productCount = 0;
    int actualProductCount;
    String productUrl = "https://www.saucedemo.com/inventory.html";
    List<String> expectedProductNames;
    List<Double> expectedProductPrice;
    List<String> actualProductNames;
    List<Double> actualProductPrice;
    List<String> expectedAddedProductNames = new ArrayList<>();

    //Constructor that will be automatically called as soon as the object of the class is created
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyProductPageTitle() {
        Assert.assertEquals("Expected and actual product page title do not match", "Products", getTextFromElement(productPageTitle));
    }

    public boolean verifyProductPageUrl() {
        return productUrl.equals(driver.getCurrentUrl());
    }

    public void selectProductSorter(String sorter) {
        Assert.assertFalse("Product sorter select box is multiple", isElementMultiple(productSorter));
        switch (sorter) {
            case "Name (A to Z)":
                selectElementFromDropDownByVisibleText(productSorter, "Name (A to Z)");
                break;
            case "Name (Z to A)":
                selectElementFromDropDownByVisibleText(productSorter, "Name (Z to A)");
                break;
            case "Price (low to high)":
                selectElementFromDropDownByVisibleText(productSorter, "Price (low to high)");
                break;
            case "Price (high to low)":
                selectElementFromDropDownByVisibleText(productSorter, "Price (high to low)");
                break;
            default:
                System.out.println("Invalid product sorter");
        }
        Assert.assertEquals("Expected and actual selected product sorter do not match", sorter, getSelectedElementsFromDropDown(productSorter));
    }

    public void verifyProductSorting() {
        expectedProductNames = new ArrayList<>();
        expectedProductPrice = new ArrayList<>();
        actualProductNames = new ArrayList<>();
        actualProductPrice = new ArrayList<>();

        for (WebElement productName : productNameList) {
            actualProductNames.add(String.valueOf(getTextFromElement(productName)));
            expectedProductNames.add(String.valueOf(getTextFromElement(productName)));
        }
        for (WebElement productPrice : productPriceList) {
            actualProductPrice.add(Double.valueOf(getTextFromElement(productPrice).replace("$", "")));
            expectedProductPrice.add(Double.valueOf(getTextFromElement(productPrice).replace("$", "")));
        }
        if (getSelectedElementsFromDropDown(productSorter).equals("Name (A to Z)")) {
            Collections.sort(expectedProductNames);
            Assert.assertEquals("Products are not sorted by name in ascending order", expectedProductNames, actualProductNames);
        } else if (getSelectedElementsFromDropDown(productSorter).equals("Name (Z to A)")) {
            Collections.sort(expectedProductNames);
            Collections.reverse(expectedProductNames);
            Assert.assertEquals("Products are not sorted by name in descending order", expectedProductNames, actualProductNames);
        } else if (getSelectedElementsFromDropDown(productSorter).equals("Price (low to high)")) {
            Collections.sort(expectedProductPrice);
            Assert.assertEquals("Products are not sorted by price in ascending order", expectedProductPrice, actualProductPrice);
        } else if (getSelectedElementsFromDropDown(productSorter).equals("Price (high to low)")) {
            Collections.sort(expectedProductPrice);
            Collections.reverse(expectedProductPrice);
            Assert.assertEquals("Products are not sorted by price in descending order", expectedProductPrice, actualProductPrice);
        }
    }

    public void addProduct(String product) {
        switch (product) {
            case ("backpack"):
                clickOnAddToCart(backpackAddToCartBtn);
                expectedAddedProductNames.add(getTextFromElement(backpackName));
                Assert.assertTrue("Backpack remove button is not displayed", backpackRemoveBtn.isDisplayed());
                break;
            case ("bike light"):
                clickOnAddToCart(bikeLightAddToCartBtn);
                expectedAddedProductNames.add(getTextFromElement(bikeLightName));
                Assert.assertTrue("Bike light remove button is not displayed", bikeLightRemoveBtn.isDisplayed());
                break;
            case ("bolt t-shirt"):
                clickOnAddToCart(boltTShirtAddToCartBtn);
                expectedAddedProductNames.add(getTextFromElement(boltTShirtName));
                Assert.assertTrue("Bolt T-Shirt remove button is not displayed", boltTShirtRemoveBtn.isDisplayed());
                break;
            case ("jacket"):
                clickOnAddToCart(jacketAddToCartBtn);
                expectedAddedProductNames.add(getTextFromElement(jacketName));
                Assert.assertTrue("Jacket remove button is not displayed", jacketRemoveBtn.isDisplayed());
                break;
            case ("onesie"):
                clickOnAddToCart(onesieAddToCartBtn);
                expectedAddedProductNames.add(getTextFromElement(onesieName));
                Assert.assertTrue("Onesie remove button is not displayed", onesieRemoveBtn.isDisplayed());
                break;
            case ("red t-shirt"):
                clickOnAddToCart(redTShirtAddToCartBtn);
                expectedAddedProductNames.add(getTextFromElement(redTShirtName));
                Assert.assertTrue("Red T-Shirt remove button is not displayed", redTShirtRemoveBtn.isDisplayed());
                break;
            default:
                System.out.println("Invalid product");
        }
        productCount++;
    }

    public void removeProduct(String product) {
        switch (product) {
            case ("backpack"):
                clickOnRemove(backpackRemoveBtn);
                expectedAddedProductNames.remove(getTextFromElement(backpackName));
                Assert.assertTrue("Backpack add to cart button is not displayed", backpackAddToCartBtn.isDisplayed());
                break;
            case ("bike light"):
                clickOnRemove(bikeLightRemoveBtn);
                expectedAddedProductNames.remove(getTextFromElement(bikeLightName));
                Assert.assertTrue("Bike light add to cart button is not displayed", bikeLightAddToCartBtn.isDisplayed());
                break;
            case ("bolt t-shirt"):
                clickOnRemove(boltTShirtRemoveBtn);
                expectedAddedProductNames.remove(getTextFromElement(boltTShirtName));
                Assert.assertTrue("Bolt T-Shirt add to cart button is not displayed", boltTShirtAddToCartBtn.isDisplayed());
                break;
            case ("jacket"):
                clickOnRemove(jacketRemoveBtn);
                expectedAddedProductNames.remove(getTextFromElement(jacketName));
                Assert.assertTrue("Jacket add to cart button is not displayed", jacketAddToCartBtn.isDisplayed());
                break;
            case ("onesie"):
                clickOnRemove(onesieRemoveBtn);
                expectedAddedProductNames.remove(getTextFromElement(onesieName));
                Assert.assertTrue("Onesie add to cart button is not displayed", onesieAddToCartBtn.isDisplayed());
                break;
            case ("red t-shirt"):
                clickOnRemove(redTShirtRemoveBtn);
                expectedAddedProductNames.remove(getTextFromElement(redTShirtName));
                Assert.assertTrue("Red T-Shirt add to cart button is not displayed", redTShirtAddToCartBtn.isDisplayed());
                break;
            default:
                System.out.println("Invalid product");
        }
        productCount--;
    }

    public List<String> getExpectedAddedProductNames() {
        return expectedAddedProductNames;
    }

    public void verifyShoppingCartBadge() {
        if (productCount > 0) {
            actualProductCount = Integer.parseInt(getTextFromElement(shoppingCartBadge));
            Assert.assertTrue("Shopping cart badge is not displayed", shoppingCartBadge.isDisplayed());
            Assert.assertEquals("Expected and actual shopping cart badge do not match", productCount, actualProductCount);
        }
    }

    public void clickOnAddToCart(WebElement productAddToCartBtn) {
        if (productAddToCartBtn.isDisplayed()) {
            Assert.assertTrue("Expected and actual product add to cart button do not match", getTextFromElement(productAddToCartBtn).equalsIgnoreCase("add to cart"));
            clickOnElement(productAddToCartBtn);
        }
    }

    public void clickOnRemove(WebElement productRemoveBtn) {
        if (productRemoveBtn.isDisplayed()) {
            Assert.assertTrue("Expected and actual product remove button do nat match", getTextFromElement(productRemoveBtn).equalsIgnoreCase("remove"));
            clickOnElement(productRemoveBtn);
        }
    }

    public void clickOnShoppingCart() {
        clickOnElement(shoppingCartLink);
    }
}