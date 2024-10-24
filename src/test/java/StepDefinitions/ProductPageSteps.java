package StepDefinitions;

import Pages.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static Helpers.Hooks.driver;

public class ProductPageSteps {
    ProductPage productPage;

    @Given("user has opened product page")
    public void userHasOpenedProductPage() {
        productPage = new ProductPage(driver);
        productPage.verifyProductPageTitle();
    }

    @When("user sorts products by {string}")
    public void userSortsProductsBy(String sorter) {
        productPage.selectProductSorter(sorter);
    }

    @Then("products are successfully sorted")
    public void productsAreSuccessfullySorted() {
        productPage.verifyProductSorting();
    }

    @When("user adds product {string} to the cart")
    public void userAddsProductToTheCart(String product) {
        productPage.addProduct(product);
    }

    @Then("shopping cart badge is successfully updated")
    public void shoppingCartBadgeIsSuccessfullyUpdated() {
        productPage.verifyShoppingCartBadge();
    }

    @When("user removes product {string}")
    public void userRemovesProduct(String product) {
        productPage.removeProduct(product);
    }
}