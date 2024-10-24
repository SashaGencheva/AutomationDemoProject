Feature: Login functionality

  @Smoke @Positive
  Scenario: Successful login with valid credentials
    Given user has opened login page
    When user authenticates with username "standard_user" and password "secret_sauce"
    Then login is successful

  @Smoke @Negative
  Scenario Outline: Unsuccessful login with invalid credentials
    Given user has opened login page
    When user authenticates with username "<username>" and password "<password>"
    Then login is unsuccessful "<message>"
    Examples:
      | username      | password     | message                                                                   |
      | user1         | password123  | Epic sadface: Username and password do not match any user in this service |
      | standard_user | password123  | Epic sadface: Username and password do not match any user in this service |
      | user1         | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      |               | secret_sauce | Epic sadface: Username is required                                        |
      | standard_user |              | Epic sadface: Password is required                                        |