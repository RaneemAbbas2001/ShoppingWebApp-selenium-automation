package AppPages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoggedinAccPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By loggedInAsText = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a");
    private By deleteAccBTN = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a");
    private By LogOutBTN = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a");
    private By cartBTN = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a");

    public LoggedinAccPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Verify logged in as - is visible at the top right of the page")
    public void verifyLoggedInAsVisible(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAsText));
        assert driver.findElement(loggedInAsText).getText().contains(username);
    }

    @Step("Click on Delete account button")
    public DeletedAccPage clickDeleteAccountButton() {
        driver.findElement(deleteAccBTN).click();
        return new DeletedAccPage(driver);
    }

    @Step("Click on logout button")
    public SignUp_LoginPage Logout() {
        driver.findElement(LogOutBTN).click();
        return new SignUp_LoginPage(driver);
    }

    @Step("Click on cart button")
    public CartPage clickOnCartBTN() {
        driver.findElement(cartBTN).click();
        return new CartPage(driver);
    }

}
