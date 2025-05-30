@addContactAPI @api
Feature: Add New Contact API

  Scenario: Add a new contact with valid dynamically generated data and token authentication
    Given I load the auth token from "src/test/resources/responses/loginResponse.json"
    And I generate valid contact test data
    When I send POST request to "/contacts" with the valid contact data and the loaded auth token
    Then the response status 201 should be displayed
    And the response body should contain all the contact details used plus the "_id" field
    Then I fetch contacts using the token and verify the new contact exists

  Scenario: Add a new contact with empty lastName and verify validation error
    Given I load the auth token from "src/test/resources/responses/loginResponse.json"
    And I generate contact test data with empty lastName
    When I send POST request to "/contacts" with the invalid contact data and the loaded auth token
    Then the response status 400 should be displayed
    And the response body should contain an error message indicating lastName is required or invalid