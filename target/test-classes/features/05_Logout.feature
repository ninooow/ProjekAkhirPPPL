Feature: Logout

  Scenario: User successfully logs out of the application
    Given the user is on the employee management page
    When the user clicks the Logout button
    And the user confirms by clicking the Keluar button
    Then the system redirects to the login page