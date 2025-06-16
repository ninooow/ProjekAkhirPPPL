@login
Feature: Authentication and Owner Dashboard Navigation

  Scenario: Login with unregistered email
    Given User is on the login page
    When User submits email "isdeaths7@example" and password "12345678"
    Then User should see an error message "Email atau password salah."

  Scenario: Login with incorrect password
    Given User is on the login page
    When User submits email "isdeaths7@gmail.com" and password "1234567890"
    Then User should see an error message "Email atau password salah."

  Scenario: Login with valid credentials
    Given User is on the login page
    When User submits email "isdeaths7@gmail.com" and password "12345678"
    Then User should be redirected to the dashboard