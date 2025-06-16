Feature: User Delete Employee Data

  Scenario: Successfully deletes employee data
    Given the user is on the manajemen karyawan page
    When the user clicks the Hapus button
    And the user confirms deletion by clicking the Ya button
    Then the system deletes the employee data
