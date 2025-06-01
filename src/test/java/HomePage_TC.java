import AppPages.HomePage;
import AppPages.ProductDetailsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class  HomePage_TC {

    private WebDriver driver;
    private HomePage homePageObj;
    private ProductDetailsPage productDetailPageObj;

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
    public void TestCase10_VerifySubscription_home_page() {
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();
        homePageObj.scrollDownFooter();
        homePageObj.Verify_text_SUBSCRIPTION();
        homePageObj.enter_SUBSCRIPTION("tst@tst.com");
        homePageObj.Verify_success_message_YouHaveBeenSuccessfullySubscribed();
    }

    @Test
    public void TestCase22_AddCartFromRecommendedItems(){
        homePageObj.navigateToHomePage();
        homePageObj.scrollDownToRecommendedItems();
        homePageObj.VerifyRECOMMENDED_ITEMSVisible();
        homePageObj.addRecommendProduct();

        productDetailPageObj = homePageObj.clickViewProductBTN();
        productDetailPageObj.verifyDetailsOfProductVisible();
    }

    @Test
    public void TestCase25_VerifyScrollUpUsingArrowButtonScrollDownFunctionality(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();
        homePageObj.scrollDownFooter();
        homePageObj.Verify_text_SUBSCRIPTION();
        homePageObj.clickOnMoveUpwardBTN();
        homePageObj.VerifyFullFledgedAutomationEngineersTextVisibleOnScreen();
    }

    @Test
    public void TestCase26_VerifyScrollUpWithoutArrowButtonScrollDownFunctionality(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();
        homePageObj.scrollDownFooter();
        homePageObj.Verify_text_SUBSCRIPTION();
        homePageObj.scrollUpToHeader();
        homePageObj.VerifyFullFledgedAutomationEngineersTextVisibleOnScreen();
    }
}
