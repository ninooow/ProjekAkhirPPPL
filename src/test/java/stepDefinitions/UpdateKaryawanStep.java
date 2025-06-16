package stepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ManajemenKaryawanPage;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

import java.time.Duration;
import java.util.Map;

import static stepDefinitions.Hooks.driver;

public class UpdateKaryawanStep {

    private ManajemenKaryawanPage manajemenKaryawanPage = new ManajemenKaryawanPage(driver);
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private ExtentTest test;

    public UpdateKaryawanStep() {
        test = ExtentReportManager.getInstance().createTest("Update Karyawan Test");
    }

    @Given("an employee named {string} is registered in the system")
    public void an_employee_named_is_registered_in_the_system(String name) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'" + name + "')]")));
        Assert.assertTrue(manajemenKaryawanPage.isNameExists(name));
        test.pass("Employee found: " + name);
    }

    @Given("I am on the page to edit {string}'s data")
    public void i_am_on_the_page_to_edit_s_data(String name) {
        manajemenKaryawanPage.clickEdit();
        test.pass("Navigated to edit form for: " + name);
    }

    @Given("another employee already exists with the email {string}")
    public void another_employee_already_exists_with_the_email(String email) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'" + email + "')]")));
        Assert.assertTrue(manajemenKaryawanPage.isEmailExists(email));
        test.pass("Existing email found: " + email);
    }

    @When("I try to change the email to {string} and save the changes")
    public void i_try_to_change_the_email_to_and_save_the_changes(String email) {
        manajemenKaryawanPage.changeEmail(email);
        manajemenKaryawanPage.clickSaveChanges();
        test.pass("Attempted to change email to: " + email);
    }

    @Then("the system should display a clear error message like {string}")
    public void the_system_should_display_a_clear_error_message_like(String expectedMessage) {
        try {
            // Tunggu alert muncul
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());

            // Ambil dan verifikasi isi alert
            String alertText = driver.switchTo().alert().getText();
            Assert.assertEquals(expectedMessage, alertText);
            driver.switchTo().alert().accept(); // atau dismiss jika sesuai sistem

            test.pass("Expected alert message displayed: " + expectedMessage);
        } catch (Exception e) {
            String path = ScreenshotUtil.takeScreenshot(driver, "update_email_error");
            test.fail("Alert message not as expected or error occurred").addScreenCaptureFromPath(path);
            throw e;
        }
    }

    @When("I update the form with the following information and save:")
    public void i_update_the_form_with_the_following_information_and_save(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        manajemenKaryawanPage.fillUpdateKaryawanValid(
                data.get("Name"),
                data.get("Contact Number"),
                data.get("Email"),
                data.get("Password")
        );
        manajemenKaryawanPage.clickSaveChanges();
        test.pass("Updated form with valid data");
    }

    @Then("the information for employee is updated in the system")
    public void the_information_for_employee_is_updated_in_the_system() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Muhammad Farhan Pera']")));
        Assert.assertEquals("Muhammad Farhan Pera", manajemenKaryawanPage.nameUpdate("Muhammad Farhan Pera"));
        Assert.assertEquals("08998765432", manajemenKaryawanPage.contactUpdate("08998765432"));
        Assert.assertEquals("farhana@gmail.com", manajemenKaryawanPage.emailUpdate("farhana@gmail.com"));
        test.pass("Employee data updated successfully");
        test.addScreenCaptureFromPath(ScreenshotUtil.takeScreenshot(driver, "update_success"));
    }
}
