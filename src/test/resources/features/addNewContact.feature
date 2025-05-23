@tag
Feature: Test Add New Contact functionality

  @TC4.1
  Scenario: Verify if user can add new contact
    Given user is in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the Contact List page
    When hit the Add New Contact button
    And user enter details in all fields
      | firstname | lastname  | birthdate  | email           | phone     | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Ivy       | Alcantara | 2000-02-26 | ivy@example.com | 123456789 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    Then new user is added in the contact list page

  @TC4.2
  Scenario: Verify that the system can successfully add a new user with a last name that is 22 characters long
    Given user is in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the Contact List page
    When hit the Add New Contact button
    And user enter details in all fields
    And user enter details in all fields
      | firstname | lastname                 | birthdate  | email               | phone     | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Gil       | Gilgeous-Alexander Great | 2000-06-26 | dagreat@example.com | 123456789 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    Then user is not added in the list

  @TC4.3
  Scenario: Verify that the user cannot add a new contact if required fields is empty.
    Given user is in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the Contact List page
    When hit the Add New Contact button
    And user enter details in all fields
    And user enter details in all fields
      | firstname | lastname           | birthdate  | email               | phone     | street1 | street2 | city    | stateProvince | postalCode | country     |
      | {empty}   | Gilgeous-Alexander | 2000-06-26 | dagreat@example.com | 123456789 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    Then user is not added in the list

  @TC4.4
  Scenario: Verify that the user cannot add new contact with invalid birthdate format
    Given user is in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the Contact List page
    When hit the Add New Contact button
    And user enter details in all fields
    And user enter details in all fields
      | firstname | lastname           | birthdate  | email               | phone     | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Gil       | Gilgeous-Alexander | 06-26-2006 | dagreat@example.com | 123456789 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    Then user is not added in the list

  @TC4.5
  Scenario: Verify that the phone field accepts 11 digits.
    Given user is in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the Contact List page
    When hit the Add New Contact button
    And user enter details in all fields
    And user enter details in all fields
      | firstname | lastname           | birthdate  | email               | phone       | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Gil       | Gilgeous-Alexander | 2000-02-26 | dagreat@example.com | 09976880471 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    Then user is not added in the list

  @TC4.6
  Scenario: Verify that the phone field accepts 16 digits
    Given user is in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the Contact List page
    When hit the Add New Contact button
    And user enter details in all fields
    And user enter details in all fields
      | firstname | lastname           | birthdate  | email               | phone           | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Gil       | Gilgeous-Alexander | 2000-02-26 | dagreat@example.com | 000000000000000 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    Then user is not added in the list

  @TC4.7
  Scenario: Verify if phone accept alphanumeric
    Given user is in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the Contact List page
    When hit the Add New Contact button
    And user enter details in all fields
    And user enter details in all fields
      | firstname | lastname           | birthdate  | email               | phone       | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Gil       | Gilgeous-Alexander | 2000-02-26 | dagreat@example.com | 099768804ab | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    Then user is not added in the list

  @TC4.8
  Scenario: Verify if phone accept only characters
    Given user is in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the Contact List page
    When hit the Add New Contact button
    And user enter details in all fields
    And user enter details in all fields
      | firstname | lastname           | birthdate  | email               | phone     | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Gil       | Gilgeous-Alexander | 2000-02-26 | dagreat@example.com | asdfghjkl | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    Then user is not added in the list

  @TC4.9
  Scenario: Verify if phone accept only characters
    Given user is in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the Contact List page
    When hit the Add New Contact button
    And user enter details in all fields
    And user enter details in all fields
      | firstname | lastname           | birthdate  | email               | phone        | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Gil       | Gilgeous-Alexander | 2000-02-26 | dagreat@example.com | 093729429429 | Nia Rd  | Barihan | Malolos | Bulacan       | aaas       | Philippines |
    Then user is not added in the list

  @TC4.10
  Scenario: Verify if postal code accept alphanumeric characters
    Given user is in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the Contact List page
    When hit the Add New Contact button
    And user enter details in all fields
    And user enter details in all fields
      | firstname | lastname           | birthdate  | email               | phone        | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Gil       | Gilgeous-Alexander | 2000-02-26 | dagreat@example.com | 093729429429 | Nia Rd  | Barihan | Malolos | Bulacan       | abcde123   | Philippines |
    Then user is not added in the list
