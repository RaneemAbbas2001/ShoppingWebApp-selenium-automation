package AppPages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DeletedAccPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By accDeletedHeader = By.xpath("//h2[@data-qa='account-deleted']");
    private By continueBTN = By.xpath("//a[@data-qa='continue-button']");

    public DeletedAccPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Verify that Account Deleted text is Visible")
    public void verifyAccountDeletedVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accDeletedHeader));
        assert driver.findElement(accDeletedHeader).getText().equals("ACCOUNT DELETED!");
    }

    @Step("Click On Continue button")
    public HomePage clickContinueButton() {
        driver.findElement(continueBTN).click();
        return new HomePage(driver);
    }
}
