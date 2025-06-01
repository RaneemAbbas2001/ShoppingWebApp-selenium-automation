package AppPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PaymentPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By nameOnCardInput = By.xpath("//*[@id=\"payment-form\"]/div[1]/div/input");
    private By cardNumberInput = By.xpath("//*[@id=\"payment-form\"]/div[2]/div/input");
    private By cvcInput = By.xpath("//*[@id=\"payment-form\"]/div[3]/div[1]/input");
    private By expiryMonthInput = By.xpath("//*[@id=\"payment-form\"]/div[3]/div[2]/input");
    private By expiryYearInput = By.xpath("//*[@id=\"payment-form\"]/div[3]/div[3]/input");
    private By PayAndConfirm = By.id("submit");
    private By orderSuccessMessage = By.id("success_message");

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Fill payment checkout details")
    public void fillPaymentDetails(String nameOnCard, String cardNumber, String cvc, String expiryMonth, String expiryYear){
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameOnCardInput)).sendKeys(nameOnCard);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberInput)).sendKeys(cardNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cvcInput)).sendKeys(cvc);
        wait.until(ExpectedConditions.visibilityOfElementLocated(expiryMonthInput)).sendKeys(expiryMonth);
        wait.until(ExpectedConditions.visibilityOfElementLocated(expiryYearInput)).sendKeys(expiryYear);
    }

    @Step("Click on pay and confirm button")
    public OrderPlacedPage clickOnPayAndConfirmOrder(){
        driver.findElement(PayAndConfirm).click();
        return new OrderPlacedPage(driver);
    }

    @Step("Verify that Order Success Message is Visible")
    public void verifyOrderSuccessMessageVisible() throws InterruptedException {
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage));
        Assert.assertTrue(successMessage.isDisplayed(), "Verification failed: Order success message is not displayed.");
        System.out.println("Verification successful: Order success message is visible.");
    }

}
