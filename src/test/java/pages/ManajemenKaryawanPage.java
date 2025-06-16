package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;

import java.time.Duration;
import java.util.List;

public class ManajemenKaryawanPage {
    WebDriver driver;
    WebDriverWait wait;

    public ManajemenKaryawanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private By tambahKaryawanButton = By.xpath("//button[contains(@class,'btn btn-primary btn-tambah-karyawan')]");
    private By tambahSubmitButton = By.xpath("//button[@type='submit' and contains(text(),'Tambah')]");
    private By createName = By.name("name");
    private By createContact = By.name("contact");
    private By createEmail = By.name("email");
    private By createPassword = By.name("password");
    private By errorMessages = By.className("invalid-feedback");

    private By editKaryawan = new By.ByXPath("//button[@data-name='Naufal']");
    private By nameInput = new By.ById("karyawan-name");
    private By contactInput = new By.ById("karyawan-contact");
    private By emailInput = new By.ById("karyawan-email");
    private By passwordInput = new By.ById("karyawan-password");
    private By buttonSave = new By.ByXPath("//button[text()='Simpan']");

    private final By deleteModal = By.id("Hapuskaryawan");
    private final By deleteKaryawan = By.xpath("//button[contains(@class, 'deleteKaryawan') and @data-name='Muhammad Farhan Pera']");
    private final By confirmDeleteBtn = By.xpath("//button[@type='submit' and normalize-space()='Ya']");
    private final By cancelDeleteBtn = By.xpath("//button[@type='button' and normalize-space()='Batal']");
    private final By logoutBtn = By.cssSelector("a[href='#logoutModal']");
    private final By logoutModal = By.id("logoutModal");
    private final By confirmLogoutBtn = By.xpath("//button[@type='submit' and normalize-space()='Keluar']");

    public boolean isNameExists(String iName){
        By name = new By.ByXPath("//td[text()='" + iName + "']");
        return driver.findElement(name).isDisplayed();
    }

    public boolean isEmailExists(String iEmail){
        By email = new By.ByXPath("//td[text()='" + iEmail + "']");
        return driver.findElement(email).isDisplayed();
    }

    public void clickEdit(){
        wait.until(ExpectedConditions.elementToBeClickable(editKaryawan));
        driver.findElement(editKaryawan).click();
    }

    public void fillUpdateKaryawanValid(String name, String contact, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        driver.findElement(nameInput).click();
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);

        driver.findElement(contactInput).click();
        driver.findElement(contactInput).clear();
        driver.findElement(contactInput).sendKeys(contact);

        driver.findElement(emailInput).click();
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);

        driver.findElement(passwordInput).click();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void changeEmail(String newEmail) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        driver.findElement(emailInput).click();
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(newEmail);
    }

    public void clickSaveChanges() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonSave));
        driver.findElement(buttonSave).click();
    }

    public boolean alertMessage(String expectedMessage) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        return alertText.contains(expectedMessage);
    }

    public String nameUpdate(String tName){
        By name = new By.ByXPath("//td[text()='"+tName+"']");
        return driver.findElement(name).getText();
    }
    public String contactUpdate(String tContact){
        By contact = new By.ByXPath("//td[text()='"+tContact+"']");
        return driver.findElement(contact).getText();
    }
    public String emailUpdate(String tEmail){
        By email = new By.ByXPath("//td[text()='"+tEmail+"']");
        return driver.findElement(email).getText();
    }

    public void clickTambahKaryawan() {
        driver.findElement(tambahKaryawanButton).click();
    }

    public void fillCreateNowKaryawanForm(String name, String contact, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createName));

        driver.findElement(createName).sendKeys(name);

        driver.findElement(createContact).sendKeys(contact);

        driver.findElement(createEmail).sendKeys(email);

        driver.findElement(createPassword).sendKeys(password);
    }

    public void clickSubmitTambahKaryawan() {
        wait.until(ExpectedConditions.elementToBeClickable(tambahSubmitButton));
        driver.findElement(tambahSubmitButton).click();
    }

    public boolean isRequiredFieldErrorsDisplayed() {
        List<WebElement> errors = driver.findElements(errorMessages);
        return errors.stream().allMatch(WebElement::isDisplayed);
    }


    public void clickDeleteKaryawanButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteKaryawan));
        driver.findElement(deleteKaryawan).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteModal));
    }

    public void confirmDeletion() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.confirmDeleteBtn));
        this.driver.findElement(this.confirmDeleteBtn).click();
        this.wait.until(ExpectedConditions.invisibilityOfElementLocated(this.deleteModal));
    }

    public void cancelDeletion() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.cancelDeleteBtn));
        this.driver.findElement(this.cancelDeleteBtn).click();
        this.wait.until(ExpectedConditions.invisibilityOfElementLocated(this.deleteModal));
    }

    public void clickLogout() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.logoutBtn));
        this.driver.findElement(this.logoutBtn).click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.logoutModal));
    }

    public void confirmLogout() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.confirmLogoutBtn));
        this.driver.findElement(this.confirmLogoutBtn).click();
        this.wait.until(ExpectedConditions.invisibilityOfElementLocated(this.logoutModal));
    }
}
