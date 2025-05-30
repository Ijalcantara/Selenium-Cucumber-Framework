@tag @login @ui
Feature: Test login functionality

  @tag1.1
  Scenario: Verify if the user can log in using valid credentials.
    Given browser is open
    And user is on landing/log in page
    When user enter a valid email
      | email             |
      | mamanya@gmail.com |
    And user enter a valid password
      | password   |
      | {password} |
    And hit Submit
    Then user is navigate to Contact List Page

  #@tag1.2
  #Scenario: Verify that the application not allows a user to log in with a valid email and incorrect password.
    #Given browser is open
    #And user is on landing/log in page
    #When user enter a valid email
      #| email             |
      #| mamanya@gmail.com |
    #And user enter an invalid password
      #| password   |
      #| {password} |
    #And hit Submit
    #Then the login fails and display an error message "Incorrect username or password"
#
  #@tag1.3
  #Scenario: Verify that the application not allows a user to log in with a invalid email and correct password.
    #Given browser is open
    #And user is on landing/log in page
    #When user enter a invalid email
      #| email                 |
      #| invaliduser@gmail.com |
    #And user enter a valid password
      #| password   |
      #| {password} |
    #And hit Submit
    #Then the login fails and display an error message "Incorrect username or password"
#
  #@tag1.4
  #Scenario: Verify that the user is unable to log in with a blank or empty password field.
    #Given browser is open
    #And user is on landing/log in page
    #When user enter a valid email
      #| email                 |
      #| invaliduser@gmail.com |
    #And user leave an empty password
      #| password |
      #| {empty}  |
    #And hit Submit
    #Then the login fails and display an error message "Incorrect username or password"
#
  #@tag1.5
  #Scenario: Verify if the user can log in using valid credentials created in the API
    #Given browser is open
    #And user is on landing/log in page
    #When user enter a valid email
      #| email               |
      #| mimaymoody@gmail.com |
    #And user enter a valid password
      #| password   |
      #| {password} |
    #And hit Submit
    #Then user is navigate to Contact List Page
