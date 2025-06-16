package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ManajemenKaryawanPage;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;


public class Hooks {

    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    @Before(value = "not @login", order = 0)
    public void setUp() throws InterruptedException{
        extent = ExtentReportManager.getInstance();
        driver = new ChromeDriver();
        driver.get("http://103.87.66.184/login");
        driver.manage().window().maximize();

        LoginPage Login = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        ManajemenKaryawanPage manajemenKaryawanPage = new ManajemenKaryawanPage(driver);

        // Login otomatis
        Login.enterEmail("isdeaths7@gmail.com");
        Login.enterPassword("12345678");
        Login.clickLogin();

        Thread.sleep(3000);
        dashboardPage.goToManajemenKaryawan();

    }

    @After("not @login")
    public void tearDown(Scenario scenario) {
        // Tambahkan ke report
        if (extent != null) {
            test = extent.createTest(scenario.getName());

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
        }
    }
}