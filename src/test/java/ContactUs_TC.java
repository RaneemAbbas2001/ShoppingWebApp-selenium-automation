import AppPages.ContactUsPage;
import AppPages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class ContactUs_TC {

    private WebDriver driver;
    private HomePage homePageObj;
    private ContactUsPage contactUsObj;
    private String name = "Ahmed";
    private String email = "ahmed@test.com";
    private String subject = "Need test";
    private String message = "Test contact us form ";
    private String filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "testDataFiles", "test.docx").toString();

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
    public void TestCase6_ContactUsForm(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        contactUsObj = homePageObj.clickContactUsBTN();
        contactUsObj.verifyGetInTouchVisible();
        contactUsObj.fillContactForm(name,email,subject,message);
        contactUsObj.uploadFile(filePath);
        contactUsObj.clickSubmitButton();
        contactUsObj.acceptSuccessAlert();
        contactUsObj.verifySuccessMessageVisible();

        homePageObj = contactUsObj.returnHome();
    }
}
