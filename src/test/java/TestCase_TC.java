import AppPages.HomePage;
import AppPages.TestCasesPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase_TC {

    private WebDriver driver;
    private HomePage homePageObj;
    private TestCasesPage testCasesObj;

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
    public void TestCase7_Verify_Test_Cases_Page(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        testCasesObj = homePageObj.clickTestCasesBTN();
        testCasesObj.Verify_user_navigated_to_testCases_page_successfully();
    }

}
