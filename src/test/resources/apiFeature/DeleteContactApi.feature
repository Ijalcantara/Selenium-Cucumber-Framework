@deleteAPI @api
Feature: Delete Contact API

  Background: 
    Given token is loaded from "src/test/resources/responses/loginResponse.json"
    And contactId is loaded from "src/test/resources/responses/validContactResponse.json"

  Scenario: Delete a contact successfully
    When I send DELETE request to "/contacts/{contactId}"
    Then 200 response status is displayed
