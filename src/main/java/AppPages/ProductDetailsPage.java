package AppPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By ProductDetailsSection = By.xpath("/html/body/section/div/div/div[2]");
    private By productName = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/h2");
    private By Category = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[1]");
    private By Price = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/span");
    private By Availability = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[2]");
    private By Condition = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[3]");
    private By Brand = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[4]");
    private By quantityNumberField = By.id("quantity");
    private By addCartBTN = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/button");
    private By viewCartBTN = By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a/u");
    private By writeYourReviewLink = By.xpath("/html/body/section/div/div/div[2]/div[3]/div[1]/ul/li/a");
    private By nameField = By.id("name");
    private By emailAddressField = By.id("email");
    private By reviewField = By.id("review");
    private By submitReviewBTN = By.id("button-review");
    private By successMessAfterReview = By.xpath("//*[@id=\"review-section\"]/div/div/span");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Verify that user has landed at product details page successfully")
    public void verifyUser_landed_product_detail_page() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsSection));
    }

    @Step("Verify that the details of the product is visible")
    public void verifyDetailsOfProductVisible() {
        WebElement product_name = driver.findElement(productName);
        Assert.assertTrue(product_name.isDisplayed(), "product name is not displayed");

        WebElement category = driver.findElement(Category);
        Assert.assertTrue(category.isDisplayed(), "category is not displayed");

        WebElement price = driver.findElement(Price);
        Assert.assertTrue(price.isDisplayed(), "price is not displayed");

        WebElement availability = driver.findElement(Availability);
        Assert.assertTrue(availability.isDisplayed(), "availability is not displayed");

        WebElement condition = driver.findElement(Condition);
        Assert.assertTrue(condition.isDisplayed(), "condition is not displayed");

        WebElement brand = driver.findElement(Brand);
        Assert.assertTrue(brand.isDisplayed(), "brand is not displayed");
    }

    @Step("Increase product quantity")
    public void increaseQuantity(int number) {
        WebElement quantityField = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityNumberField));
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(number));
        System.out.println("Quantity set to: " + number);
    }

    @Step("Add product to cart")
    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addCartBTN)).click();
        System.out.println("Clicked 'Add to Cart' button.");
    }

    @Step("Click on view cart button")
    public CartPage viewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartBTN)).click();
        System.out.println("Clicked 'View Cart' button.");
        return new CartPage(driver);
    }

    @Step("Verify that Write your review section is visible")
    public void VerifyWriteYourReviewVisible() {
        WebElement reviewHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(writeYourReviewLink));
        Assert.assertTrue(reviewHeader.isDisplayed(), "Verification failed: 'Write Your Review' header is not displayed.");
        System.out.println("Verification successful: 'Write Your Review' section is visible.");

    }

    @Step("Write your review in review section and submit it")
    public void writeReview_submit(String Name, String email, String Review) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(Name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(reviewField)).sendKeys(Review);
        wait.until(ExpectedConditions.elementToBeClickable(submitReviewBTN)).click();
        System.out.println("Review submitted with Name: " + Name + ", Email: " + email);
    }

    @Step("Verify that the success message for review is displayed")
    public void VerifySuccessMessageReview() {
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessAfterReview));
        Assert.assertTrue(successMessage.isDisplayed(), "Verification failed: Review success message is not displayed.");
        Assert.assertEquals(successMessage.getText().trim(), "Thank you for your review.", "Review success message text mismatch.");
        System.out.println("Verification successful: Success message 'Thank you for your review.' is visible.");
    }

}
