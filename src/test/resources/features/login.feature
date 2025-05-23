@tag
Feature: Test login functionality

  @tag1
  Scenario Outline: Verify if the user can log in using valid credentials.
    Given browser is open
    And user is on landing/log in page
    When user enter a valid email "<email>"
    And user enter a valid password "<password>"
    And hit Submit
    Then user is navigate to Contact List Page

    Examples: 
      | email             | password   |
      | mamanya@gmail.com | {password} |
      | mamako@gmail.com  | {password} |

  @tag2
  Scenario Outline: Verify that the application not allows a user to log in with a valid email and incorrect password.
    Given browser is open
    And user is on landing/log in page
    When user enter a valid email "<email>"
    And user enter a invalid password "<password>"
    And hit Submit
    Then the login fails and display an error message

    Examples: 
      | email             | password   |
      | mamanya@gmail.com | {password} |
      | mamako@gmail.com  | {password} |

  @tag3
  Scenario Outline: Verify that the application not allows a user to log in with a invalid email and correct password.
    Given browser is open
    And user is on landing/log in page
    When user enter a invalid email "<email>"
    And user enter a valid password "<password>"
    And hit Submit
    Then the login fails and display an error message

    Examples: 
      | email                 | password   |
      | invaliduser@gmail.com | {password} |
      

  @tag4
  Scenario Outline: Verify that the user is unable to log in with a blank or empty password field.
    Given browser is open
    And user is on landing/log in page
    When user enter a valid email "<email>"
    And user leave an empty password "<password>"
    And hit Submit
    Then the login fails and display an error message

    Examples: 
      | email               | password   |
      | validuser@gmail.com | {empty} |
