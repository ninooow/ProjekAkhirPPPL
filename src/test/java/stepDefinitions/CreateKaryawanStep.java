package stepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ManajemenKaryawanPage;
import pages.DashboardPage;

import java.time.Duration;
import java.util.Map;

import utils.ExtentReportManager;
import utils.ScreenshotUtil;


import static org.junit.Assert.assertTrue;

public class CreateKaryawanStep {

    private WebDriver driver;
    private WebDriverWait wait;
    private ManajemenKaryawanPage manajemenKaryawanPage;
    private DashboardPage dashboardPage;

    @Before
    public void setUp() {
        driver = Hooks.driver; // Ambil dari Hooks yang mengatur browser
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        manajemenKaryawanPage = new ManajemenKaryawanPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    // =====================================================
    // NAVIGATION SCENARIO
    // =====================================================

    @Given("the user is on the dashboard page")
    public void the_user_is_on_the_dashboard_page() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("settingsLink")));
    }

    @When("the user clicks the Manajemen Karyawan navigation menu")
    public void the_user_clicks_the_manajemen_karyawan_navigation_menu() {
        dashboardPage.goToManajemenKaryawan();
    }

    @Then("the user should be redirected to the Manajemen Karyawan page for create")
    public void the_user_should_be_redirected_to_the_manajemen_karyawan_page_for_create() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        assertTrue("Expected URL to contain 'settings' but was: " + currentUrl, currentUrl.contains("settings"));
    }

    // =====================================================
    // EMPTY REQUIRED FIELDS SCENARIO
    // =====================================================

    @Given("the user is on the manajemen karyawan page for create")
    public void the_user_is_on_the_manajemen_karyawan_page_for_create() {
        dashboardPage.goToManajemenKaryawan();
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        assertTrue("Expected URL to contain 'settings' but was: " + currentUrl, currentUrl.contains("settings"));
    }

    @When("the user clicks the Tambah Karyawan button")
    public void the_user_clicks_the_tambah_karyawan_button() {
        manajemenKaryawanPage.clickTambahKaryawan();
    }

    @And("the user clicks the Tambah button without filling any fields")
    public void the_user_clicks_the_tambah_button_without_filling_any_fields() {
        manajemenKaryawanPage.clickSubmitTambahKaryawan();
    }

    @Then("the user should see an error message for each required field")
    public void the_user_should_see_an_error_message_for_each_required_field() {
        Assert.assertTrue(manajemenKaryawanPage.isRequiredFieldErrorsDisplayed());
    }

    // =====================================================
    // DUPLICATE EMAIL SCENARIO
    // =====================================================

    @Given("an employee with email {string} already exists")
    public void an_employee_with_email_already_exists(String email) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'" + email + "')]")));
        Assert.assertTrue("Email " + email + " should exist", manajemenKaryawanPage.isEmailExists(email));
    }

    /*********************************************************************
     *  STEP: isi form karyawan (DataTable) ­– diperbarui
     ********************************************************************/
    @When("the user fills the new employee form with the following data:")
    public void the_user_fills_the_new_employee_form_with_the_following_data(DataTable dataTable) {

        // ── 2. Ambil data dari tabel dan isi form ──
        Map<String, String> data = dataTable.asMaps().get(0);

        manajemenKaryawanPage.fillCreateNowKaryawanForm(
                data.get("name"),
                data.get("contact"),
                data.get("email"),
                data.get("password")
        );
    }


    @And("the user clicks the Tambah button")
    public void the_user_clicks_the_tambah_button() {
        manajemenKaryawanPage.clickSubmitTambahKaryawan();
    }

    @Then("the user should see a {string} error message")
    public void the_user_should_see_a_error_message(String expectedMessage) {
        Assert.assertTrue(manajemenKaryawanPage.alertMessage(expectedMessage));
    }

    // =====================================================
    // SUCCESSFULLY ADD NEW EMPLOYEE SCENARIO
    // =====================================================

    @When("the user fills the form with valid employee data")
    public void the_user_fills_the_form_with_valid_employee_data() {
        manajemenKaryawanPage.fillCreateNowKaryawanForm(
                "Naufal",
                "081200567330",
                "salamanto@gmail.com",
                "12345678"
        );
    }

    @Then("the new employee {string} should appear in the employee list")
    public void the_new_employee_should_appear_in_the_employee_list(String employeeName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='" + employeeName + "']")));
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + employeeName + "']")).isDisplayed());
    }
}
