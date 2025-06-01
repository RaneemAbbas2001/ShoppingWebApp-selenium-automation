import AppPages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login_TC {

    private WebDriver driver;
    private HomePage homePageObj;
    private SignUp_LoginPage Signup_loginPageObj;
    private LoggedinAccPage loggedinAccountPageObj;
    private DeletedAccPage deleteAccObj;
    private String name = "Rylee Rhodes";
    private String CorrectEmail = "wiluqacizu@mailinator.com";
    private String CorrectPassword = "123";
    private String incorrectEmail = "invalid@example.com";
    private String incorrectPassword = "wrongpassword";
    private String LogInEmail = "User@User.com";
    private String LogInPassword = "12345";
    private String LogInName = "User";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        homePageObj = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void TestCase2_Login_User_with_correct_email_password() {
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        Signup_loginPageObj = homePageObj.clickSignupLoginButton();
        Signup_loginPageObj.verifyLoginToYourAccountVisible();

        loggedinAccountPageObj = Signup_loginPageObj.fillLoginDetails(CorrectEmail, CorrectPassword);
        loggedinAccountPageObj.verifyLoggedInAsVisible(name);

        deleteAccObj = loggedinAccountPageObj.clickDeleteAccountButton();
        deleteAccObj.verifyAccountDeletedVisible();
    }

    @Test
    public void TestCase3_Login_User_with_incorrect_email_password() {
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        Signup_loginPageObj = homePageObj.clickSignupLoginButton();
        Signup_loginPageObj.verifyLoginToYourAccountVisible();
        Signup_loginPageObj.fillLoginDetails(incorrectEmail, incorrectPassword);

        WebElement Your_email_or_password_is_incorrect = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div[1]/form/p"));
        Assert.assertTrue(Your_email_or_password_is_incorrect.isDisplayed(), "Sentence is not displayed");
    }

    @Test
    public void TestCase4_LogoutUser() {
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        Signup_loginPageObj = homePageObj.clickSignupLoginButton();
        Signup_loginPageObj.verifyLoginToYourAccountVisible();

        loggedinAccountPageObj = Signup_loginPageObj.fillLoginDetails(LogInEmail, LogInPassword);
        loggedinAccountPageObj.verifyLoggedInAsVisible(LogInName);

        Signup_loginPageObj = loggedinAccountPageObj.Logout();
        Signup_loginPageObj.verifyLoginToYourAccountVisible();
        Signup_loginPageObj.verifyNewUserSignupVisible();
    }
}
