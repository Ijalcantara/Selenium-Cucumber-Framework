@addUserAPI @api
Feature: Create User Account

  Scenario: Create user account using valid data
    Given I have valid user data
      | firstName | lastName | email               | password   |
      | Jemimah       | Alcantara  | mimaymoody@gmail.com | {password} |
    When I send a POST request to "/users" to create the user
    Then the response status code should be 201
    And the response body should contain the user details
    Then I can retrieve the user by email "addApiUser@test.com" and verify details

  Scenario: Fail to create user account using invalid email
    Given I have user data with invalid email
      | firstName | lastName | email         | password   |
      | api       | addUser  | invalid.Email | {password} |
    When I send a POST request to "/users" to create the user
    Then the response status code should be 400
    And the response body should contain an error message about invalid email
