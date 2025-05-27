@tag
Feature: Test signup functionality

  @TC2.1
  Scenario: Verify that the user cannot submit the form when required fields are empty or blank
    Given user hit Sign up
    Then user is on addUser page
    When user leave all fields empty
    And hit the signUp Submit button
    Then user is not added and an error message is displayed "User validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required., email: Email is invalid, password: Path `password` is required."
    And the form should not be submitted

  @TC2.2
  Scenario: Verify if the app accepts a password with a length of 4 characters.
    Given user hit Sign up
    Then user is on addUser page
    When user input in all fields
      | firstName | lastName | email                   | password |
      | Sample    | Error    | passwordError@gmail.com | pass     |
    And hit the signUp Submit button
    Then user is not added and an error message is displayed "User validation failed: email: Email is invalid, password: Path `password` (`pass`) is shorter than the minimum allowed length (7)."
    And the form should not be submitted

  @TC2.3
  Scenario: Verify if the app accepts a password with a length of 10 characters.
    Given user hit Sign up
    Then user is on addUser page
    When user input in all fields
      | firstName | lastName | email             | password   |
      | Carmen      | Fernandez      | minmin@test.com | {password} |
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
    Then user is not added and an error message is displayed "User validation failed: email: Email is invalid"
    And the form should not be submitted

  @TC2.5
  Scenario: Verify that the app cannot add a user with an invalid email address.
    Given user hit Sign up
    Then user is on addUser page
    When user input in all fields
      | firstName | lastName | email             | password   |
      | John      | Doe      | mamanya@gmail.com | {password} |
    And hit the signUp Submit button
    Then user is not added and an error message is displayed "Email address is already in use"
    And the form should not be submitted
