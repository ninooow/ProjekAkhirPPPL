//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private By emailInput = By.id("email");
    private By passInput = By.id("password");
    private By tombolLogin = By.xpath("//form[@id='loginForm']//button[@type='submit']");
    By errorMessage = By.xpath("//form[@id='loginForm']//div[@class='text-danger mt-1']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        this.driver.findElement(this.emailInput).sendKeys(new CharSequence[]{email});
    }

    public void enterPassword(String password) {
        this.driver.findElement(this.passInput).sendKeys(new CharSequence[]{password});
    }

    public void clickLogin() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.elementToBeClickable(this.tombolLogin));
        this.driver.findElement(this.tombolLogin).click();
    }

    public String getErrorMessage() {
        return this.driver.findElement(this.errorMessage).getText();
    }
}
