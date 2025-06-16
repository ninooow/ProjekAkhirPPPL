package stepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.*;
import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ScreenshotUtil;

public class LoginStep {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ExtentTest test;

    public LoginStep() {
        this.driver = LoginHooks.getDriver();
        this.loginPage = new LoginPage(this.driver);
        this.dashboardPage = new DashboardPage(this.driver);
        this.test = LoginHooks.test;
    }

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        test.info("Berada di halaman login");
    }

    @When("User submits email {string} and password {string}")
    public void user_submits_credentials(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        test.info("Submit login dengan email: " + email);
    }

    @Then("User should be redirected to the dashboard")
    public void is_dashboard_page() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains("dashboard"));
            dashboardPage.isAtDashboard();
            test.pass("Berhasil login dan diarahkan ke dashboard");
        } catch (Exception e) {
            String path = ScreenshotUtil.takeScreenshot(driver, "LoginGagal");
            test.fail("Gagal login ke dashboard").addScreenCaptureFromPath(path);
            throw e;
        }
    }

    @Then("User should see an error message {string}")
    public void user_should_see_error_message(String expectedMessage) {
        try {
            Assert.assertEquals(expectedMessage, loginPage.getErrorMessage());
            test.pass("Pesan error sesuai: " + expectedMessage);
        } catch (AssertionError e) {
            String path = ScreenshotUtil.takeScreenshot(driver, "LoginErrorMessageMismatch");
            test.fail("Pesan error tidak sesuai").addScreenCaptureFromPath(path);
            throw e;
        }
    }
}
