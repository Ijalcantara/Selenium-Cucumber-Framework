@loginAPI @api
Feature: User Login API

  Scenario: Verify the successful login using valid credentials
    Given I have "valid" login credentials from json file "apiTestData.json"
    When I send a POST request to "/users/login"
    Then response status code of request must be 200
    And the response body should contain user object and token string

  Scenario: Fail to log in with incorrect password
    Given I have "invalid" login credentials from json file "createUser.json"
    When I send a POST request to "/users/login"
    Then response status code of request must be 401
    And user must not log in
