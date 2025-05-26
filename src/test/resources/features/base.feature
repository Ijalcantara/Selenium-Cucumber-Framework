@tag
Feature: Test log out button functionality

  @tag5.1
  Scenario: Verify that the user can use the Log Out button
    Given browser is open
    And user is on landing/log in page
    When user enter a valid credentials
      | email             | password   |
      | mamako@gmail.com | {password} |
    And hit Submit
    Then user is navigate to Contact List Page
    When user click the logut button
    Then user is navigated to login page
