package AppPages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Acc_info_Page {

    private WebDriver driver;
    private WebDriverWait wait;
    private By AccountInfoHeader = By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/h2/b");
    private By genderMrsRadioBTN = By.id("id_gender2");
    private By passwordField = By.id("password");
    private By dayDropdown = By.id("days");
    private By monthDropdown = By.id("months");
    private By yearDropdown = By.id("years");
    private By newsletterCheckbox = By.cssSelector("#newsletter");
    private By offersCheckbox = By.cssSelector("#optin");
    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By companyField = By.id("company");
    private By addressField = By.id("address1");
    private By address2Field = By.id("address2");
    private By countryDropdown = By.id("country");
    private By stateField = By.id("state");
    private By cityField = By.id("city");
    private By zipcodeField = By.id("zipcode");
    private By mobileNumberField = By.id("mobile_number");
    private By createAccountBTN = By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/form/button");

    public Acc_info_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Step("Verify Account Info is visible")
    public void verifyEnterAccountInfoVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountInfoHeader));
        String headerText = driver.findElement(AccountInfoHeader).getText();
        if (!headerText.equals("ENTER ACCOUNT INFORMATION")) {
            throw new AssertionError("Verification failed: 'ENTER ACCOUNT INFORMATION' is not visible. Actual text: " + headerText);
        }
    }

    @Step("Fill Account details")
    public void fillAccountDetails(String password, String day, String month, String year) {
        driver.findElement(genderMrsRadioBTN).click();
        driver.findElement(passwordField).sendKeys(password);
        new Select(driver.findElement(dayDropdown)).selectByValue(day);
        new Select(driver.findElement(monthDropdown)).selectByVisibleText(month);
        new Select(driver.findElement(yearDropdown)).selectByValue(year);
    }

    @Step("Select news letter and offers checkboxes")
    public void selectNewsLetterAndOffers() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(newsletterCheckbox)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(offersCheckbox)).click();
    }

    @Step("Fill Address details")
    public void fillAddressDetails(String firstName, String lastName, String company, String address, String address2, String country, String state, String city, String zipcode, String mobileNumber) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(companyField).sendKeys(company);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(address2Field).sendKeys(address2);
        new Select(driver.findElement(countryDropdown)).selectByVisibleText(country);
        driver.findElement(stateField).sendKeys(state);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(zipcodeField).sendKeys(zipcode);
        driver.findElement(mobileNumberField).sendKeys(mobileNumber);
    }

    @Step("Click on Create account button")
    public AccCreatedPage clickCreateAccountButton() {
        driver.findElement(createAccountBTN).click();
        return new AccCreatedPage(driver);
    }
}
