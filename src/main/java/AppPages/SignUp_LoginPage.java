package AppPages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignUp_LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By NewUserSignupVisible = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/h2");
    private By nameInput = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[2]");
    private By emailAddressInput = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]");
    private By signUpBTN = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button");
    private By LoginToYourAccountVisible = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/h2");
    private By LogInemailAddress = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]");
    private By LogInPass = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]");
    private By LogInBTN = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button");


    public SignUp_LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Verify that new user signup test is visible")
    public void verifyNewUserSignupVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(NewUserSignupVisible));
    }

    @Step("Fill signup details")
    public Acc_info_Page fillSignupDetails(String name, String email) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailAddressInput).sendKeys(email);
        driver.findElement(signUpBTN).click();
        return new Acc_info_Page(driver);
    }

    @Step("Verify that login to your account text is visible")
    public void verifyLoginToYourAccountVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginToYourAccountVisible));
    }

    @Step("Fill log in details")
    public LoggedinAccPage fillLoginDetails(String email, String password) {
        driver.findElement(LogInemailAddress).sendKeys(email);
        driver.findElement(LogInPass).sendKeys(password);
        driver.findElement(LogInBTN).click();
        return new LoggedinAccPage(driver);
    }
}

