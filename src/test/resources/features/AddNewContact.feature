@ui @addNewContact
Feature: Test Add New Contact functionality

  @TC4.1
  Scenario: Verify if user can add new contact
    Given user is already in login page
    When user input a credentials to open an account
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the ContactList page
    When hit the addNewContact button
    And user enter details in all fields
      | firstName | lastName  | birthdate  | email                | phone     | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Ivy Jemimah      | Alcantara | 2000-02-26 | addedUserUi@example.com | 123456789 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    When click addnewContact submit button
    Then new user is added in the contact list page

  @TC4.2
  Scenario: Verify that the system can successfully add a new user with a last name that is 22 characters long
    Given user is already in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the ContactList page
    When hit the addNewContact button
    And user enter details in all fields
      | firstName | lastName                 | birthdate  | email                    | phone     | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Gil       | Gilgeous-Alexander Great | 2000-02-26 | testLastname@example.com | 123456789 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    When click addnewContact submit button
    Then error occured and display error message "Contact validation failed: lastName: Path `lastName` (`Gilgeous-Alexander Great`) is longer than the maximum allowed length (20)."

  @TC4.3
  Scenario: Verify that the user cannot add a new contact if required fields is empty.
    Given user is already in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the ContactList page
    When hit the addNewContact button
    And user enter details in all fields
      | firstName | lastName           | birthdate  | email                 | phone     | street1 | street2 | city    | stateProvince | postalCode | country     |
      | {empty}   | Gilgeous-Alexander | 2000-02-26 | testEmpty@example.com | 123456789 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    When click addnewContact submit button
    Then error occured and display error message "Contact validation failed: firstName: Path `firstName` is required."

  @TC4.4
  Scenario: Verify that the user cannot add new contact with invalid birthdate format
    Given user is already in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the ContactList page
    When hit the addNewContact button
    And user enter details in all fields
      | firstName | lastName  | birthdate  | email                     | phone     | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Test      | Birthdate | 02-26-2006 | testBirthdate@example.com | 123456789 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    When click addnewContact submit button
    Then error occured and display error message "Contact validation failed: birthdate: Birthdate is invalid"

  @TC4.5
  Scenario: Verify that the phone field accepts 11 digits.
    Given user is already in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the ContactList page
    When hit the addNewContact button
    And user enter details in all fields
      | firstName | lastName | birthdate  | email                  | phone     | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Accepted     | Phone    | 2000-02-26 | testPhone1@example.com | 123456789 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    When click addnewContact submit button
    Then new user is added in the contact list page

  @TC4.6
  Scenario: Verify that the phone field accepts 16 digits
    Given user is already in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the ContactList page
    When hit the addNewContact button
    And user enter details in all fields
      | firstName | lastName | birthdate  | email                  | phone            | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Test      | Phone 2  | 2000-02-26 | testPhone2@example.com | 0000000000000000 | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    When click addnewContact submit button
    Then error occured and display error message "Contact validation failed: phone: Path `phone` (`0000000000000000`) is longer than the maximum allowed length (15)."

  @TC4.7
  Scenario: Verify if phone accept alphanumeric
    Given user is already in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the ContactList page
    When hit the addNewContact button
    And user enter details in all fields
      | firstName | lastName | birthdate  | email                 | phone      | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Test      | Alpha    | 2000-02-26 | testAlpha@example.com | 1234567acv | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    When click addnewContact submit button
    Then error occured and display error message "Contact validation failed: phone: Phone number is invalid"

  @TC4.8
  Scenario: Verify if phone accept only characters
    Given user is already in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the ContactList page
    When hit the addNewContact button
    And user enter details in all fields
      | firstName | lastName | birthdate  | email                 | phone     | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Yagit     | Yumi     | 2000-02-26 | yagitYumi@example.com | asdfghjkl | Nia Rd  | Barihan | Malolos | Bulacan       |       3000 | Philippines |
    When click addnewContact submit button
    Then error occured and display error message "Contact validation failed: phone: Phone number is invalid"

  @TC4.9
  Scenario: Verify if postalCode accept only characters
    Given user is already in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the ContactList page
    When hit the addNewContact button
    And user enter details in all fields
      | firstname | lastname | birthdate  | email             | phone        | street1 | street2 | city    | stateProvince | postalCode | country     |
      | Buding    | Ding     | 2000-02-26 | adeng@example.com | 093729429429 | Nia Rd  | Barihan | Malolos | Bulacan       | aaas       | Philippines |
    When click addnewContact submit button
    Then error occured and display error message "Contact validation failed: postalCode: Postal code is invalid"

  @TC4.10
  Scenario: Verify if postal code accept alphanumeric characters
    Given user is already in login page
    When user input a valid credentials
      | email             | password   |
      | mamanya@gmail.com | {password} |
    And hit the Submit button
    Then user is navigated in the ContactList page
    When hit the addNewContact button
    And user enter details in all fields
      | firstName | lastName | birthdate  | email                | phone        | street1 | street2 | city    | stateProvince | postalCode | country     |
      | MAMA      | NATIN TO | 2000-02-26 | mamanila@example.com | 093729429429 | Nia Rd  | Barihan | Malolos | Bulacan       | abcde123   | Philippines |
    When click addnewContact submit button
    Then error occured and display error message "Contact validation failed: postalCode: Postal code is invalid"
