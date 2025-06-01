package AppPages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String HomePageURL = "https://automationexercise.com/";
    private By signup_LoginButton = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a");
    private By homePageSlider = By.id("slider");
    private By contactUsBTN = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[8]/a");
    private By testCasesBTN = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a");
    private By ProductsBTN = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a");
    private By subscriptionFooter = By.xpath("//*[@id=\"footer\"]/div[1]/div/div/div[2]/div/h2");
    private By descriptionEmail = By.id("susbscribe_email");
    private By descriptionEmailArrow = By.id("subscribe");
    private By successSubscriptionAlert = By.xpath("//*[@id=\"success-subscribe\"]/div");
    private By cartBTN = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a");
    private By featureHeader = By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/h2");
    private By viewProductBTN = By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[3]/div/div[2]/ul/li/a");
    private By product1 = By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div/div[1]/div[1]/a");
    private By product2 = By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[3]/div/div[1]/div[1]/a");
    private By product3 = By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[4]/div/div[1]/div[1]/a");
    private By continueBTN = By.xpath("//*[@id=\"cartModal\"]/div/div/div[3]/button");
    private By catgoryLeftSideBar = By.xpath("/html/body/section[2]/div/div/div[1]/div/h2");
    private By brandLeftSideBar = By.cssSelector("body > section:nth-child(3) > div > div > div.col-sm-3 > div.left-sidebar > div.brands_products");
    private By womenBTN = By.xpath("//*[@id=\"accordian\"]/div[1]/div[1]/h4/a");
    private By womenSubBTN = By.xpath("//*[@id=\"Women\"]/div/ul/li[1]/a");
    private By brandNameBTN = By.cssSelector("body > section:nth-child(3) > div > div > div.col-sm-3 > div.left-sidebar > div.brands_products > div > ul > li:nth-child(2) > a");
    private By RecommendHeader = By.xpath("/html/body/section[2]/div/div/div[2]/div[2]/h2");
    private By addRecommendProduct = By.xpath("//*[@id=\"recommended-item-carousel\"]/div/div[2]/div[1]/div/div/div/a");
    private By moveUpwardBTN = By.id("scrollUp");
    private By FullFledgedText = By.xpath("//*[@id=\"slider-carousel\"]/div/div[1]/div[1]/h2");
    private By Header = By.xpath("//*[@id=\"header\"]/div");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Navigate to home page")
    public void navigateToHomePage() {

        driver.get(HomePageURL);
    }

    @Step("Verify home page is visible")
    public void verifyHomePageVisible() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(homePageSlider));
    }

    @Step("Click on SignUp/Login button")
    public SignUp_LoginPage clickSignupLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signup_LoginButton)).click();
        return new SignUp_LoginPage(driver);
    }

    @Step("Click on Contact us button")
    public ContactUsPage clickContactUsBTN() {
        wait.until(ExpectedConditions.elementToBeClickable(contactUsBTN)).click();
        return new ContactUsPage(driver);
    }

    @Step("Click on TestCases button")
    public TestCasesPage clickTestCasesBTN() {
        wait.until(ExpectedConditions.elementToBeClickable(testCasesBTN)).click();
        return new TestCasesPage(driver);
    }

    @Step("Click on Products button")
    public AllProductsPage clickProductsBTN() {
        wait.until(ExpectedConditions.elementToBeClickable(ProductsBTN)).click();
        return new AllProductsPage(driver);
    }

    @Step("Scroll down to footer")
    public void scrollDownFooter() {
        WebElement footerElement = driver.findElement(subscriptionFooter);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footerElement);
    }

    @Step("Verify text subscription is visible")
    public void Verify_text_SUBSCRIPTION() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionFooter));
        String footerText = driver.findElement(subscriptionFooter).getText();
        assert footerText.equals("SUBSCRIPTION");
    }

    @Step("Enter subscription email")
    public void enter_SUBSCRIPTION(String desciptEmail) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionEmail)).sendKeys(desciptEmail);
        wait.until(ExpectedConditions.elementToBeClickable(descriptionEmailArrow)).click();
    }

    @Step("Verify that success message You Have Been Successfully Subscribed is visible")
    public void Verify_success_message_YouHaveBeenSuccessfullySubscribed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successSubscriptionAlert));
    }

    @Step("Click on Cart button")
    public CartPage clickCartBTN() {
        wait.until(ExpectedConditions.elementToBeClickable(cartBTN)).click();
        return new CartPage(driver);
    }

    @Step("Click on View products button")
    public ProductDetailsPage clickViewProductBTN() {
        wait.until(ExpectedConditions.elementToBeClickable(viewProductBTN)).click();
        return new ProductDetailsPage(driver);
    }

    @Step("Scroll down to feature section")
    public void scrollDownToFeature() {
        WebElement featureElement = driver.findElement(featureHeader);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", featureElement);
    }

    @Step("Add products to cart")
    public void addProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(product1)).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueBTN)).click();

        wait.until(ExpectedConditions.elementToBeClickable(product2)).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueBTN)).click();

        wait.until(ExpectedConditions.elementToBeClickable(product3)).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueBTN)).click();
    }

    @Step("Scroll to category left side bar")
    public void scrollToCategoryLeftSideBar() {
        WebElement categoryElement = driver.findElement(catgoryLeftSideBar);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", categoryElement);
    }

    @Step("Verify that categories left side bar is visible")
    public void VerifyCategoriesVisibleLeftSidebar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(catgoryLeftSideBar));
    }

    @Step("Verify that Brand left side bar is visible")
    public void VerifyBrandVisibleLeftSidebar() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(brandLeftSideBar));
    }

    @Step("Click on Women products button")
    public void clickOnWomenBTN() {

        wait.until(ExpectedConditions.elementToBeClickable(womenBTN)).click();
    }

    @Step("Click on sub women option button")
    public Category_Brand_Page clickOnSubWomenBTN() {
        wait.until(ExpectedConditions.elementToBeClickable(womenSubBTN)).click();
        return new Category_Brand_Page(driver);
    }

    @Step("Click on brand button")
    public Category_Brand_Page clickBrandBTN() {
        wait.until(ExpectedConditions.elementToBeClickable(brandNameBTN)).click();
        return new Category_Brand_Page(driver);
    }

    @Step("Scroll down to Recommended items section")
    public void scrollDownToRecommendedItems() {
        WebElement recommendedElement = driver.findElement(RecommendHeader);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", recommendedElement);
    }

    @Step("Verify that recommended items is visible")
    public void VerifyRECOMMENDED_ITEMSVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(RecommendHeader));
    }

    @Step("Add products from recommended items")
    public void addRecommendProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(addRecommendProduct)).click();
    }

    @Step("Click on Move upward button")
    public void clickOnMoveUpwardBTN() {

        wait.until(ExpectedConditions.elementToBeClickable(moveUpwardBTN)).click();
    }

    @Step("Verify full fledged text is  visible")
    public void VerifyFullFledgedAutomationEngineersTextVisibleOnScreen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(FullFledgedText));
    }

    @Step("Scroll up to the page header")
    public void scrollUpToHeader() {
        WebElement headerElement = driver.findElement(Header);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", headerElement);
    }
}
