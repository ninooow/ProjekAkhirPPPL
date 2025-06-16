package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class LoginHooks {
    private static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    @Before("@login")
    public void setUp(Scenario scenario) {
        extent = ExtentReportManager.getInstance();
        test = extent.createTest(scenario.getName());
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://103.87.66.184"); // Tambahkan http:// agar valid
        }
    }

    @After("@login")
    public void tearDown(Scenario scenario) {
        if (extent != null) {
            if (scenario.isFailed()) {
                String screenshotPath = ScreenshotUtil.takeScreenshot(driver, scenario.getName().replaceAll(" ", "_"));
                test.fail("Scenario Failed");
                test.addScreenCaptureFromPath(screenshotPath);
            } else {
                test.pass("Scenario Passed");
            }

            extent.flush(); // simpan report
        }

        if (driver != null) {
            driver.quit();
            driver = null; // <- tambahkan ini agar driver di-reset
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
