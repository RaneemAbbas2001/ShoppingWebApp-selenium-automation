package AppPages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AllProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private By allProductsHeader = By.xpath("/html/body/section[2]/div/div/div[2]/div/h2");
    private By allProductsList = By.xpath("/html/body/section[2]/div/div/div[2]/div");
    private By ViewProductBTN = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a");
    private By SearchBar = By.id("search_product");
    private By SearchIcon = By.id("submit_search");
    private By SearchedProductVisible = By.xpath("/html/body/section[2]/div/div/div[2]/div/h2");
    private By SearchedProductList = By.xpath("/html/body/section[2]/div/div/div[2]/div");
    private By firstProduct = By.xpath("/html/body/section[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/div[1]");
    private By firstProductAddToCart = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/a");
    private By secondProduct = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[3]/div");
    private By secondProductAddToCart = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[3]/div/div[1]/div[2]/div/a");
    private By continueBTN = By.cssSelector("#cartModal > div > div > div.modal-footer > button");
    private By ViewCartBTN = By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a/u");
    private By sleevesDressAdd = By.cssSelector("body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div > div.col-sm-4 > div > div.single-products > div.productinfo.text-center > a");


    public AllProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    @Step("Verify that user is navigated to all products page successfully")
    public void Verify_user_navigated_ALL_PRODUCTS_page_successfully() {
        WebElement productsHeader = driver.findElement(allProductsHeader);
        Assert.assertTrue(productsHeader.isDisplayed(), "list is not displayed");
    }

    @Step("Verify that products list is visible")
    public void verifyProductsListVisible() {
        WebElement productList = driver.findElement(allProductsList);
        Assert.assertTrue(productList.isDisplayed(), "list is not displayed");
    }

    @Step("Click on View product for specific product")
    public ProductDetailsPage clickViewProductOfFirstProduct() {
        driver.findElement(ViewProductBTN).click();
        return new ProductDetailsPage(driver);
    }

    @Step("Search for a product")
    public void SearchProduct(String productName) {
        WebElement searchField = driver.findElement(SearchBar);
        searchField.sendKeys("Sleeveless Dress");

        WebElement searchIcon = driver.findElement(SearchIcon);
        searchIcon.click();
    }

    @Step("Verify that the searched product is visible")
    public void VerifySearchedProductVisible() {
        WebElement searchProductsDisplayed = driver.findElement(SearchedProductVisible);
        Assert.assertTrue(searchProductsDisplayed.isDisplayed(), "Sentence is not displayed");
    }


    @Step("Verify that all products related to the search are visible")
    public void verifyAllSearchedProductsVisible() {
        WebElement allProductsDisplayed = driver.findElement(SearchedProductList);
        Assert.assertTrue(allProductsDisplayed.isDisplayed(), "All Products are not displayed");
    }

    @Step("Hover over products")
    public void hoverOverProducts() throws InterruptedException {
        WebElement firstProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
        actions.moveToElement(firstProductElement).perform();
        WebElement firstAddToCartButton = wait.until(ExpectedConditions.elementToBeClickable(firstProductAddToCart));
        firstAddToCartButton.click();

        WebElement continueButtonElement = wait.until(ExpectedConditions.elementToBeClickable(continueBTN));
        continueButtonElement.click();

        WebElement secondProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(secondProduct));
        actions.moveToElement(secondProductElement).perform();
        WebElement secondAddToCartButton = wait.until(ExpectedConditions.elementToBeClickable(secondProductAddToCart));
        secondAddToCartButton.click();
    }

    @Step("Click on view cart button")
    public CartPage clickViewCartBTN() {
        wait.until(ExpectedConditions.elementToBeClickable(ViewCartBTN)).click();
        return new CartPage(driver);
    }

    @Step("Add searched products to the cart")
    public void addSearchedProduct(){
        wait.until(ExpectedConditions.elementToBeClickable(sleevesDressAdd)).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueBTN)).click();
    }
}
