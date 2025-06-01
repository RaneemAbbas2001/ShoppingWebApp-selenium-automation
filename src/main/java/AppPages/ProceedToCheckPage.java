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

public class ProceedToCheckPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By addressDetailsHeader = By.xpath("//*[@id=\"cart_items\"]/div/div[2]/h2");
    private By ReviewYourOrderHeader = By.xpath("//*[@id=\"cart_items\"]/div/div[4]/h2");
    private By comment = By.xpath("//*[@id=\"ordermsg\"]/textarea");
    private By placeOrderBTN = By.xpath("//*[@id=\"cart_items\"]/div/div[7]/a");
    private By deliveryAddressLine1 = By.xpath("//*[@id=\"address_delivery\"]/li[4]");
    private By deliveryAddressLine2 = By.xpath("//*[@id=\"address_delivery\"]/li[5]");
    private By billingAddressLine1 = By.xpath("//*[@id=\"address_invoice\"]/li[4]");
    private By billingAddressLine2 = By.xpath("//*[@id=\"address_invoice\"]/li[5]");
    private By deleteBTN = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a");

    public ProceedToCheckPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Verify that Address Details and Review Your Order section is visible")
    public void  VerifyAddressDetails_ReviewYourOrder(){

        WebElement Address_DetailsDisplayed = driver.findElement(addressDetailsHeader);
        Assert.assertTrue(Address_DetailsDisplayed.isDisplayed(), "Address Details is not displayed");

        WebElement Review_Your_OrderDisplayed = driver.findElement(ReviewYourOrderHeader);
        Assert.assertTrue(Review_Your_OrderDisplayed.isDisplayed(), "Review Your Order is not displayed");
    }

    @Step("Scroll to comment area and write your comment ")
    public void scrollToCommentArea_WriteComment(){
        WebElement commentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(comment));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", commentElement);
        System.out.println("Scrolled to the comment area.");

        commentElement.sendKeys("Test comment area");
        System.out.println("Typed 'Test comment area' into the comment field.");
    }

    @Step("Click on place order button")
    public PaymentPage clickOnPlaceOrder(){
        driver.findElement(placeOrderBTN).click();
        return new PaymentPage(driver);
    }

    @Step("Verify that The Delivery Address is the Same Address Filled At Registration")
    public void VerifyTheDeliveryAddressSameAddressFilledAtRegistration(String expectedDeliveryAddressLine1, String expectedDeliveryAddressLine2) {
        WebElement actualDeliveryAddressLine1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryAddressLine1));
        Assert.assertEquals(actualDeliveryAddressLine1Element.getText().trim(), expectedDeliveryAddressLine1);

        WebElement actualDeliveryAddressLine2Element = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryAddressLine2));
        Assert.assertEquals(actualDeliveryAddressLine2Element.getText().trim(), expectedDeliveryAddressLine2);
    }

    @Step("Verify that The Billing Address is the Same Address Filled At Registration")
    public void VerifyBillingAddressSameAddressFilledAtRegistration(String expectedBillingAddressLine1, String expectedBillingAddressLine2) {
        WebElement actualBillingAddressLine1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddressLine1));
        Assert.assertEquals(actualBillingAddressLine1Element.getText().trim(), expectedBillingAddressLine1);

        WebElement actualBillingAddressLine2Element = wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddressLine2));
        Assert.assertEquals(actualBillingAddressLine2Element.getText().trim(), expectedBillingAddressLine2);
    }

    @Step("Click on delete button")
    public DeletedAccPage clickOnDeleteBTN(){
        driver.findElement(deleteBTN).click();
        return new DeletedAccPage(driver);
    }
}
