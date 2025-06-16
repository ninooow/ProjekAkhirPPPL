Feature: User Updates Employee Data
Background:
  Given an employee named "Naufal" is registered in the system
  And I am on the page to edit "Naufal"'s data

  Scenario: Fail to update Employee data because the email is already in use
    And another employee already exists with the email "zaidan@gmail.com"
    When I try to change the email to "zaidan@gmail.com" and save the changes
    Then the system should display a clear error message like "Error: Gagal memperbarui karyawan"

  Scenario: Successfully edit an employee's information with valid data
      When I update the form with the following information and save:
      | Field          | Value                   |
      | Name           | Muhammad Farhan Pera     |
      | Contact Number | 08998765432             |
      | Email          | farhana@gmail.com        |
      | Password       | 87654321                |

      Then the information for employee is updated in the system
