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

public class Register_TC {

    private WebDriver driver;
    private HomePage homePageObj;
    private SignUp_LoginPage Signup_loginPageObj;
    private AccCreatedPage createdAccountPageObj;
    private LoggedinAccPage loggedinAccountPageObj;
    private Acc_info_Page Account_info_Obj;
    private DeletedAccPage deleteAccObj;
    private String name = "Test User";
    private String email = "testuser" + System.currentTimeMillis() + "@example.com";
    private String password = "password123";
    private String day = "1";
    private String month = "January";
    private String year = "2000";
    private String firstName = "Test";
    private String lastName = "User";
    private String company = "Test Company";
    private String address = "123 Test Address";
    private String address2 = "Apt 4B";
    private String country = "Canada";
    private String state = "Ontario";
    private String city = "Toronto";
    private String zipcode = "M5V 2H1";
    private String mobileNumber = "1234567890";
    private String ExistName = "Rylee Rhodes";
    private String ExistEmail = "User@User.com";

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
    public void TestCase1_RegisterUser() {
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        Signup_loginPageObj = homePageObj.clickSignupLoginButton();
        Signup_loginPageObj.verifyNewUserSignupVisible();

        Account_info_Obj = Signup_loginPageObj.fillSignupDetails(name, email);
        Account_info_Obj.verifyEnterAccountInfoVisible();
        Account_info_Obj.fillAccountDetails(password, day, month, year);
        Account_info_Obj.selectNewsLetterAndOffers();
        Account_info_Obj.fillAddressDetails(firstName, lastName, company, address, address2, country, state, city, zipcode, mobileNumber);

        createdAccountPageObj = Account_info_Obj.clickCreateAccountButton();
        createdAccountPageObj.verifyAccountCreatedVisible();

        loggedinAccountPageObj = createdAccountPageObj.clickContinueButton();
        loggedinAccountPageObj.verifyLoggedInAsVisible(name);

        deleteAccObj = loggedinAccountPageObj.clickDeleteAccountButton();
        deleteAccObj.verifyAccountDeletedVisible();
        homePageObj = deleteAccObj.clickContinueButton();
    }

    @Test
    public void TestCase5_Register_User_with_existing_email(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        Signup_loginPageObj = homePageObj.clickSignupLoginButton();
        Signup_loginPageObj.verifyNewUserSignupVisible();

        Account_info_Obj = Signup_loginPageObj.fillSignupDetails(ExistName,ExistEmail);

        WebElement EmailAddressAlreadyExistError = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/p"));
        Assert.assertTrue(EmailAddressAlreadyExistError.isDisplayed(), "Verify error 'Email Address already exist!' is visible");
    }

}
