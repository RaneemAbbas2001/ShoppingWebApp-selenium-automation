package AppPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By getInTouchVisible = By.xpath("//*[@id=\"contact-page\"]/div[2]/div[1]/div/h2");
    private By nameField = By.xpath("//*[@id=\"contact-us-form\"]/div[1]/input");
    private By emailField = By.xpath("//*[@id=\"contact-us-form\"]/div[2]/input");
    private By subjectField = By.xpath("//*[@id=\"contact-us-form\"]/div[3]/input");
    private By messageField = By.id("message");
    private By uploadFileButton = By.xpath("//*[@id=\"contact-us-form\"]/div[5]/input"); // This is typically a file input <input type="file">
    private By submitButton = By.xpath("//*[@id=\"contact-us-form\"]/div[6]/input");
    private By successAlert = By.xpath("//div[@class='alert alert-success']");
    private By successMessage = By.xpath("//*[@id=\"contact-page\"]/div[2]/div[1]/div/div[2]");
    private By HomeBTN = By.xpath("//*[@id=\"form-section\"]/a/span");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Verify that Get in touch text is Visible")
    public void verifyGetInTouchVisible(){
        WebElement getInTouchHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(getInTouchVisible));
        if (!getInTouchHeader.isDisplayed()) {
            throw new AssertionError("Verification failed: 'GET IN TOUCH' header is not visible.");
        }
        System.out.println("Verified: 'GET IN TOUCH' is visible.");
    }

    @Step("Fill Contact Us Form")
    public void fillContactForm(String name, String email, String subject, String message){
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(subjectField)).sendKeys(subject);
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageField)).sendKeys(message);
    }

    @Step("Upload File")
    public void uploadFile(String filePath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(uploadFileButton)).sendKeys(filePath);
    }

    @Step("Click on Submit button")
    public void clickSubmitButton() {

        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    @Step("Accept Success alert message")
    public void acceptSuccessAlert() {

        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    @Step("Verify that success message text is Visible")
    public void verifySuccessMessageVisible() {
        WebElement successMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        if (!successMsgElement.isDisplayed()) {
            throw new AssertionError("Verification failed: Success message is not visible.");
        }
        System.out.println("Verified: Success message is visible.");
    }

    @Step("Return Back to home page")
    public HomePage returnHome(){
        wait.until(ExpectedConditions.elementToBeClickable(HomeBTN)).click();
        return new HomePage(driver);
    }
}
