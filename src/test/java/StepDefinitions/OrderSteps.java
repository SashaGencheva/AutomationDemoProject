package StepDefinitions;

import Pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static Helpers.Hooks.driver;

public class OrderSteps {
    ProductPage productPage;
    CartPage cartPage;
    CheckoutInfoPage checkoutInfoPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutCompletePage checkoutCompletePage;

    @When("user sorts products")
    public void userSortsProducts() {
        productPage = new ProductPage(driver);
        productPage.selectProductSorter("Price (high to low)");
    }

    @When("user adds product\\(s) to the cart")
    public void userAddsProductsToTheCart() {
        productPage.addProduct("bolt t-shirt");
        productPage.addProduct("jacket");
        productPage.addProduct("red t-shirt");
        productPage.verifyShoppingCartBadge();
    }

    @And("user opens shopping cart")
    public void userOpensShoppingCart() {
        productPage.clickOnShoppingCart();
        cartPage = new CartPage(driver);
        cartPage.verifyCartPageUrl();
        cartPage.verifyCartPageTitle();
    }

    @Then("added products are displayed")
    public void addedProductsAreDisplayed() {
        Assert.assertEquals("Expected and actual added products in cart page do not match", productPage.getExpectedAddedProductNames(), cartPage.getActualAddedProductNames());
    }

    @When("user proceeds to checkout")
    public void userProceedsToCheckout() {
        cartPage.clickOnCheckout();
        checkoutInfoPage = new CheckoutInfoPage(driver);
        checkoutInfoPage.verifyCheckoutInfoUrl();
        checkoutInfoPage.verifyCheckoutInfoTitle();
    }

    @And("user fills in checkout form")
    public void userFillsInCheckoutForm() {
        checkoutInfoPage.setFirstName("John");
        checkoutInfoPage.setLastName("Smith");
        checkoutInfoPage.setZipCode("98109");
        checkoutInfoPage.clickOnContinue();

        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.verifyCheckoutOverviewUrl();
        checkoutOverviewPage.verifyCheckoutOverviewTitle();
    }

    @Then("checkout overview is displayed")
    public void checkoutOverviewIsDisplayed() {
        Assert.assertEquals("Expected and actual added products in checkout overview page do not match", productPage.getExpectedAddedProductNames(), checkoutOverviewPage.getActualCheckoutOverviewProductNames());

        checkoutOverviewPage.verifyPaymentInfo();
        checkoutOverviewPage.verifyShippingInfo();
        checkoutOverviewPage.verifyProductPriceTotal();
    }

    @When("user finishes the order")
    public void userFinishesTheOrder() {
        checkoutOverviewPage.clickOnFinish();
        checkoutCompletePage = new CheckoutCompletePage(driver);
        checkoutCompletePage.verifyCheckoutCompleteUrl();
        checkoutCompletePage.verifyCheckoutCompleteTitle();
    }

    @Then("order is successfully completed")
    public void orderIsSuccessfullyCompleted() {
        checkoutCompletePage.verifyOrder();
    }
}