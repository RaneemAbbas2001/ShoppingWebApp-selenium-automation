package AppPages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccCreatedPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By accountCreatedHeader = By.xpath("//*[@id=\"form\"]/div/div/div/h2/b");
    private By continueBTN = By.xpath("//*[@id=\"form\"]/div/div/div/div/a");

    public AccCreatedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Verify that account created text is visible")
    public void verifyAccountCreatedVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedHeader));
        String headerText = driver.findElement(accountCreatedHeader).getText();
        if (!headerText.equals("ACCOUNT CREATED!")) {
            throw new AssertionError("Expected 'ACCOUNT CREATED!' but found: " + headerText);
        }
    }

    @Step("Click on continue button")
    public LoggedinAccPage clickContinueButton() {
        driver.findElement(continueBTN).click();
        return new LoggedinAccPage(driver);
    }
}

