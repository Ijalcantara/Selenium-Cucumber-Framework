@ui
Feature: Test Contact Detail page functionalities

  @TC6.1
  Scenario: Verify that the user can delete an existing contact from the list.
    Given user is in login page
    When user enter their credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the login/submit button
    Then user is on the home page
    When user click one account in the list
    And the user clicks the Delete Contact button
    And the user confirms deletion on the dialog box
    Then the contact should be deleted from the Contact List and no longer visible

