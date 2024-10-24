package StepDefinitions;

import Pages.LoginPage;
import Pages.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static Helpers.Hooks.driver;

public class LoginPageSteps {
    LoginPage loginPage;
    ProductPage productPage;
    String loginUrl = "https://www.saucedemo.com/";

    @Given("user has opened login page")
    public void userHasOpenedLoginPage() {
        driver.get(loginUrl);
        loginPage = new LoginPage(driver);
    }

    @When("user authenticates with username {string} and password {string}")
    public void userAuthenticatesWithUsernameAndPassword(String user, String pass) {
        loginPage.setUserName(user);
        loginPage.setPassword(pass);
        loginPage.clickOnLogin();
    }

    @Then("login is successful")
    public void loginIsSuccessful() {
        productPage = new ProductPage(driver);
        Assert.assertTrue("Login is unsuccessful", productPage.verifyProductPageUrl());
    }

    @Then("login is unsuccessful {string}")
    public void loginIsUnsuccessful(String errorMsg) {
        Assert.assertEquals(errorMsg, loginPage.getErrorMessage());
    }
}