@Sanity
Feature: Product page functionalities - sorting, cart badge updating

  Background: User is logged in
    Given user has opened login page
    When user authenticates with username "standard_user" and password "secret_sauce"
    Then login is successful

  Scenario: Successful product sorting by "Name (A to Z)"
    Given user has opened product page
    When user sorts products by "Name (A to Z)"
    Then products are successfully sorted

  Scenario: Successful product sorting by "Name (Z to A)"
    Given user has opened product page
    When user sorts products by "Name (Z to A)"
    Then products are successfully sorted

  Scenario: Successful product sorting by "Price (low to high)"
    Given user has opened product page
    When user sorts products by "Price (low to high)"
    Then products are successfully sorted

  Scenario: Successful product sorting by "Price (high to low)"
    Given user has opened product page
    When user sorts products by "Price (high to low)"
    Then products are successfully sorted

  Scenario: Successful shopping cart badge update after product adding and removing
    Given user has opened product page
    When user adds product "backpack" to the cart
    * user adds product "bike light" to the cart
    * user adds product "onesie" to the cart
    Then shopping cart badge is successfully updated
    When user removes product "backpack"
    And user removes product "onesie"
    And user removes product "bike light"
    Then shopping cart badge is successfully updated