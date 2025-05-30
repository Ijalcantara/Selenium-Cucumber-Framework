@ui @contactlist
Feature: Test contact list page functionality

  @TC3.1
  Scenario: Verify that the user can view the summary/list in the contact list.
    Given user is in login page
    When user input a valid credentials
      | email            | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is redirected to contact details page.
