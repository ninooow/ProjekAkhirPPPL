package stepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.ManajemenKaryawanPage;
import utils.ScreenshotUtil;

public class LogoutStep {
    WebDriver driver;
    ManajemenKaryawanPage karyawanPage;
    ExtentTest test;

    public LogoutStep() {
        this.driver = Hooks.driver;
        this.karyawanPage = new ManajemenKaryawanPage(this.driver);
        this.test = Hooks.test;
    }

    @Given("the user is on the employee management page")
    public void the_user_is_on_the_employee_management_page() {
        try {
            Assert.assertTrue(driver.getCurrentUrl().contains("settings"));
            test.pass("Berada di halaman manajemen karyawan");
        } catch (AssertionError e) {
            String path = ScreenshotUtil.takeScreenshot(driver, "Logout_Url_Salah");
            test.fail("Bukan di halaman yang benar").addScreenCaptureFromPath(path);
            throw e;
        }
    }

    @When("the user clicks the Logout button")
    public void the_user_clicks_the_logout_button() {
        karyawanPage.clickLogout();
        test.info("Klik tombol logout");
    }

    @And("the user confirms by clicking the Keluar button")
    public void the_user_confirms_logout_button() {
        karyawanPage.confirmLogout();
        test.info("Konfirmasi logout");
    }

    @Then("the system redirects to the login page")
    public void the_system_redirects_to_the_login_page() {
        try {
            Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
            test.pass("Berhasil logout dan kembali ke halaman login");
        } catch (AssertionError e) {
            String path = ScreenshotUtil.takeScreenshot(driver, "Logout_Gagal");
            test.fail("Logout gagal atau tidak diarahkan ke halaman login").addScreenCaptureFromPath(path);
            throw e;
        }
    }
}
