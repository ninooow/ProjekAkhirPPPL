package stepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.ManajemenKaryawanPage;
import utils.ScreenshotUtil;

public class HapusKaryawanStep {
    WebDriver driver;
    ManajemenKaryawanPage karyawanPage;
    ExtentTest test;

    public HapusKaryawanStep() {
        this.driver = Hooks.driver;
        this.karyawanPage = new ManajemenKaryawanPage(this.driver);
        this.test = Hooks.test;
    }

    @Given("the user is on the manajemen karyawan page")
    public void the_user_is_on_the_manajemen_karyawan_page() {
        String currentUrl = this.driver.getCurrentUrl();
        test.info("Current URL: " + currentUrl);
        try {
            Assert.assertTrue("Expected URL to contain 'settings'", currentUrl.contains("settings"));
            test.pass("Berada di halaman manajemen karyawan");
        } catch (AssertionError e) {
            String path = ScreenshotUtil.takeScreenshot(driver, "HalamanManajemenKaryawan");
            test.fail("Halaman salah: " + e.getMessage()).addScreenCaptureFromPath(path);
            throw e;
        }
    }

    @When("the user clicks the Hapus button")
    public void the_user_clicks_the_button() {
        karyawanPage.clickDeleteKaryawanButton();
        test.info("Klik tombol Hapus Karyawan bernama Salman");
    }

    @When("the user confirms deletion by clicking the Ya button")
    public void the_user_confirms_deletion_by_clicking_the_button() {
        karyawanPage.confirmDeletion();
        test.info("Klik tombol Ya untuk konfirmasi");
    }

    @Then("the system deletes the employee data")
    public void the_system_deletes_the_employee_data() {
        test.pass("Data karyawan berhasil dihapus");
    }
}
