@updateUserApi @api
Feature: Edit existing contact details

  Background: 
    Given auth token is loaded from "src/test/resources/responses/loginResponse.json"
    And I load contactId from "src/test/resources/responses/validContactResponse.json"

  Scenario: Update contact details successfully
    Given I generate valid contact test data for update
    When I send PUT request to "/contacts/{contactId}" with the updated contact data and the loaded auth token
    Then the 200 response should be displayed
    And the response body should contain all the updated contact details used plus the "contactId" field

  Scenario: Update contact details with invalid data
    Given I generate invalid contact test data for update
    When I send PUT request to "/contacts/{contactId}" with the invalid contact data and the loaded auth token
    Then the response status 400 should be displayed
    And the response body should contain an error message indicating the invalid fields
