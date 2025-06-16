//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;
    private By tombolManajemenKaryawanPage = By.id("settingsLink");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAtDashboard() {
        return this.driver.getCurrentUrl().contains("/dashboard");
    }

    public void goToManajemenKaryawan() {
        this.driver.findElement(this.tombolManajemenKaryawanPage).click();
    }
}
