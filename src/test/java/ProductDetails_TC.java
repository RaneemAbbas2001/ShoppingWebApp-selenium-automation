import AppPages.AllProductsPage;
import AppPages.HomePage;
import AppPages.ProductDetailsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetails_TC {
    private WebDriver driver;
    private HomePage homePageObj;
    private AllProductsPage allProductsPageObj;
    private ProductDetailsPage productDetailPageObj;
    private String name = "Zoro";
    private String emailAddress = "zoro@tst.com";
    private String review = "Nice product";

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
    public void TestCase21_AddReviewOnProduct(){
        homePageObj.navigateToHomePage();

        allProductsPageObj = homePageObj.clickProductsBTN();
        allProductsPageObj.Verify_user_navigated_ALL_PRODUCTS_page_successfully();

        homePageObj.scrollToCategoryLeftSideBar();
        productDetailPageObj = allProductsPageObj.clickViewProductOfFirstProduct();
        productDetailPageObj.VerifyWriteYourReviewVisible();
        productDetailPageObj.writeReview_submit(name,emailAddress,review);
        productDetailPageObj.VerifySuccessMessageReview();
    }



}
