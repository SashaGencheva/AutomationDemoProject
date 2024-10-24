Feature: Verifying order functionality

  Background: User is logged in
    Given user has opened login page
    When user authenticates with username "standard_user" and password "secret_sauce"
    Then login is successful

  @EndToEnd
  Scenario: Successful order for registered user
    Given user has opened product page
    When user sorts products
    Then products are successfully sorted
    When user adds product(s) to the cart
    And user opens shopping cart
    Then added products are displayed
    When user proceeds to checkout
    And user fills in checkout form
    Then checkout overview is displayed
    When user finishes the order
    Then order is successfully completed