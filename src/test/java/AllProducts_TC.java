import AppPages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AllProducts_TC {

    private WebDriver driver;
    private HomePage homePageObj;
    private AllProductsPage allProductsPageObj;
    private ProductDetailsPage productDetailPageObj;
    private CartPage cartPageObj;
    private SignUp_LoginPage signup_loginPageObj;
    private LoggedinAccPage loggedinAccountPageObj;
    private String productName = "Sleeveless Dress";
    private String CorrectEmail = "suzo@mailinator.com";
    private String CorrectPassword = "12345";



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
    public void TestCase8_Verify_AllProducts_product_detail_page(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        allProductsPageObj = homePageObj.clickProductsBTN();
        allProductsPageObj.Verify_user_navigated_ALL_PRODUCTS_page_successfully();
        allProductsPageObj.verifyProductsListVisible();

        productDetailPageObj = allProductsPageObj.clickViewProductOfFirstProduct();
        productDetailPageObj.verifyUser_landed_product_detail_page();
        productDetailPageObj.verifyDetailsOfProductVisible();
    }

    @Test
    public void TestCase9_SearchProduct(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        allProductsPageObj = homePageObj.clickProductsBTN();
        allProductsPageObj.Verify_user_navigated_ALL_PRODUCTS_page_successfully();
        allProductsPageObj.SearchProduct(productName);
        allProductsPageObj.VerifySearchedProductVisible();
        allProductsPageObj.verifyAllSearchedProductsVisible();
    }

    @Test
    public void TestCase20_SearchProductsVerifyCartAfterLogin(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        allProductsPageObj = homePageObj.clickProductsBTN();
        allProductsPageObj.Verify_user_navigated_ALL_PRODUCTS_page_successfully();
        homePageObj.scrollToCategoryLeftSideBar();
        allProductsPageObj.SearchProduct(productName);
        allProductsPageObj.VerifySearchedProductVisible();
        allProductsPageObj.verifyAllSearchedProductsVisible();
        homePageObj.scrollToCategoryLeftSideBar();
        allProductsPageObj.addSearchedProduct();

        homePageObj.scrollUpToHeader();
        cartPageObj = homePageObj.clickCartBTN();
        cartPageObj.VerifyCartPageDisplayed();

        signup_loginPageObj = homePageObj.clickSignupLoginButton();

        loggedinAccountPageObj = signup_loginPageObj.fillLoginDetails(CorrectEmail,CorrectPassword);
        loggedinAccountPageObj.clickOnCartBTN();

        cartPageObj.VerifyCartPageDisplayed();
    }
}
