@tag
Feature: Test Contact Detail page functionalities

  @TC6.1
  Scenario: Verify that the user can delete an existing contact from the list.
    Given user is in login page
    When user enter required valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    Then user is on the home page
    When user click one account in the list
    Then user will be navigated to the contactDetails page
    When hit the delete button
    Then user is no longer in the contact list

  @TC6.2
  Scenario: Verify that the user can use the Return to Contact List button
    Given user is in login page
    When user enter required valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    Then user is on the home page
    When user click one account in the list
    Then user will be navigated to the contactDetails page
    When hit the Return to Contact list button
    Then user will navigated in the list page
