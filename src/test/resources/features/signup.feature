@tag
Feature: Test signup functionality

  @TC2.1
  Scenario: Verify that the user cannot submit the form when required fields are empty or blank
    Given user hit Sign up
    Then user is on addUser page
    When user leave all fields empty
    And hit the signUp Submit button
    Then user is not added and an error message is displayed
    And the form should not be submitted

  @TC2.2
  Scenario: Verify if the app accepts a password with a length of 4 characters.
    Given user hit Sign up
    Then user is on addUser page
    When user input in all fields
      | firstName | lastName | email                   | password |
      | Sample    | Error    | passwordError@gmail.com | pass     |
    And hit the signUp Submit button
    Then user is not added and an error message is displayed
    And the form should not be submitted

  @TC2.3
  Scenario: Verify if the app accepts a password with a length of 10 characters.
    Given user hit Sign up
    Then user is on addUser page
    When user input in all fields
      | firstName | lastName | email             | password   |
      | John      | Doe      | cesskobe@test.com | {password} |
    And hit the signUp Submit button
    Then new user is successfully added and redirected to Contact List

  @TC2.4
  Scenario: Verify that the app cannot add a user with an invalid email address.
    Given user hit Sign up
    Then user is on addUser page
    When user input in all fields
      | firstName | lastName | email          | password   |
      | John      | Doe      | emailerror.com | {password} |
    And hit the signUp Submit button
    Then user is not added and an error message is displayed
    And the form should not be submitted

  @TC2.5
  Scenario: Verify that the app cannot add a user with an invalid email address.
    Given user hit Sign up
    Then user is on addUser page
    When user input in all fields
      | firstName | lastName | email             | password   |
      | John      | Doe      | mamanya@gmail.com | {password} |
    And hit the signUp Submit button
    Then user is not added and an error message is displayed
    And the form should not be submitted
