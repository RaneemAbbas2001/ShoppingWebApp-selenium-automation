import AppPages.AllProductsPage;
import AppPages.Category_Brand_Page;
import AppPages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Category_TC {

    private WebDriver driver;
    private HomePage homePageObj;
    private Category_Brand_Page category_brandPageObj;
    private AllProductsPage allProductsPageObj;
    private String expectedText = "WOMEN - DRESS PRODUCTS";
    private String expectedText2 = "MEN - TSHIRTS PRODUCTS";
    private String expectedText3 = "BRAND - H&M PRODUCTS";

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
    public void TestCase18_ViewCategoryProducts(){
        homePageObj.navigateToHomePage();
        homePageObj.scrollToCategoryLeftSideBar();
        homePageObj.VerifyCategoriesVisibleLeftSidebar();
        homePageObj.clickOnWomenBTN();

        category_brandPageObj = homePageObj.clickOnSubWomenBTN();
        category_brandPageObj.verifyCategoryPageVisible(expectedText);
        category_brandPageObj.clickOnMenBTN();
        category_brandPageObj.clickOnSubMenBTN();
        category_brandPageObj.verifyCategoryPage(expectedText2);
    }

    @Test
    public void TestCase19_ViewCartBrandProducts(){
        homePageObj.navigateToHomePage();
        allProductsPageObj = homePageObj.clickProductsBTN();
        homePageObj.scrollToCategoryLeftSideBar();
        homePageObj.VerifyBrandVisibleLeftSidebar();

        category_brandPageObj = homePageObj.clickBrandBTN();
        category_brandPageObj.verifyBrandPage(expectedText3);
    }
}
