@tag
Feature: To verify if the existing contact details in the list can modify/update/edit.

  @TC6.1
  Scenario: Verify if the user can edit fields
  Given user is in login page
  When user enter required valid credentials
  | email             | password   |
  | mamanya@gmail.com | {password} |
  And click the editContact button
  Then user can see all of the contact list
  When user click one contact in the table
  And hit EditContact button
  Then user can edit details of the chosen contact
  When user enter new name
  | firstName | lastName | country |
  | Sample Edit  | ForLastName | CANADA |
  And hit the ediContact submit button
  Then user successfully edit the contact
  When hit Return to Contact button
  Then user will navigated to the contactList page
  When hit logout button
  Then user is redirected to login page

