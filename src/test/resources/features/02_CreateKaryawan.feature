Feature: User Create Employee Data

  Scenario: Navigate to Manajemen Karyawan page from dashboard
    Given the user is on the dashboard page
    When the user clicks the Manajemen Karyawan navigation menu
    Then the user should be redirected to the Manajemen Karyawan page for create

  Scenario: Attempt to add an employee with empty required fields
    Given the user is on the manajemen karyawan page for create
    When the user clicks the Tambah Karyawan button
    And the user clicks the Tambah button without filling any fields
    Then the user should see an error message for each required field

  Scenario: Attempt to add an employee with a duplicate email
    Given the user is on the manajemen karyawan page for create
    And an employee with email "zaidan@gmail.com" already exists
    When the user clicks the Tambah Karyawan button
    And the user fills the new employee form with the following data:
      | name            | contact      | email               | password  |
      | Zaidann         | 089663452345 | zaidan@gmail.com    | 12345678  |
    And the user clicks the Tambah button
    Then the user should see a "Gagal menambahkan karyawan" error message

  Scenario: Successfully add a new employee with valid data
    Given the user is on the manajemen karyawan page
    When the user clicks the Tambah Karyawan button
    And the user fills the new employee form with the following data:
      | name            | contact      | email               | password  |
      | Naufal          | 081200567330 | salamanto@gmail.com   | 12345678  |
    And the user clicks the Tambah button
    Then the new employee "Naufal" should appear in the employee list