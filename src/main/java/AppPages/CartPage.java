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

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By descriptionFooter = By.xpath("//*[@id=\"footer\"]/div[1]/div/div/div[2]/div/h2");
    private By descriptionEmail = By.id("susbscribe_email");
    private By descriptionEmailArrow = By.id("subscribe");
    private By successSubscriptionAlert = By.xpath("//*[@id=\"success-subscribe\"]/div");
    private By item1 = By.id("product-1");
    private By item2 = By.id("product-2");
    private By item1Price = By.xpath("//*[@id=\"product-1\"]/td[3]");
    private By item1Quantity = By.xpath("//*[@id=\"product-1\"]/td[4]");
    private By item1TotalPrice = By.xpath("//*[@id=\"product-1\"]/td[5]");
    private By item2Price = By.xpath("//*[@id=\"product-2\"]/td[3]");
    private By item2Quantity = By.xpath("//*[@id=\"product-2\"]/td[4]");
    private By item2TotalPrice = By.xpath("//*[@id=\"product-2\"]/td[5]");
    private By productQuantity = By.xpath("//*[@id=\"product-2\"]/td[4]/button");
    private By cartInfo = By.xpath("//*[@id=\"cart_info_table\"]");
    private By proceedToCheckOutBTN = By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a");
    private By Register_LoginBTN = By.xpath("//*[@id=\"checkoutModal\"]/div/div/div[2]/p[2]/a/u");
    private By removeProduct1BTN = By.xpath("//*[@id=\"product-1\"]/td[6]/a");
    private By removeProduct2BTN = By.xpath("//*[@id=\"product-2\"]/td[6]/a");
    private By removeProduct3BTN = By.xpath("//*[@id=\"product-3\"]/td[6]/a");
    private By emptyCartMessage = By.id("empty_cart");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Scroll down to footer ")
    public void scrollDownFooter(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @Step("Verify that subscription text is displayed ")
    public void Verify_text_SUBSCRIPTION() {
        WebElement SUBSCRIPTIONDisplayed = driver.findElement(descriptionFooter);
        Assert.assertTrue(SUBSCRIPTIONDisplayed.isDisplayed(), "Sentence is not displayed");
    }

    @Step("Enter email at subscription filed")
    public void enter_SUBSCRIPTION(String desciptEmail){
        WebElement subscribeField = driver.findElement(descriptionEmail);
        subscribeField.sendKeys("r@gmail.com");

        WebElement arrowBTN = driver.findElement(descriptionEmailArrow);
        arrowBTN.click();
    }

    @Step("Verify that success message You Have Been Successfully Subscribed is displayed ")
    public void Verify_success_message_YouHaveBeenSuccessfullySubscribed() {
        WebElement successMessDisplayed = driver.findElement(successSubscriptionAlert);
        Assert.assertTrue(successMessDisplayed.isDisplayed(), "Sentence is not displayed");
    }

    @Step("Verify that both products are added to cart successfully ")
    public void  Verify_both_products_added_Cart() {

        WebElement product1 = driver.findElement(item1);
        Assert.assertTrue(product1.isDisplayed(), "First product is not displayed");

        WebElement product2 = driver.findElement(item2);
        Assert.assertTrue(product2.isDisplayed(), "Second product is not displayed");
    }

    @Step("Verify that product's price , quantity and total price are displayed ")
    public void  VerifyProducts_prices_quantity_totalPrice(){

        WebElement productPrice1=driver.findElement(item1Price);
        WebElement productQuantity1=driver.findElement(item1Quantity);
        WebElement productTotalPrice1=driver.findElement(item1TotalPrice);

        WebElement productPrice2=driver.findElement(item2Price);
        WebElement productQuantity2=driver.findElement(item2Quantity);
        WebElement productTotalPrice2=driver.findElement(item2TotalPrice);

        Assert.assertTrue(productPrice1.isDisplayed(), "First product price is not displayed");
        Assert.assertTrue(productQuantity1.isDisplayed(), "First product quantity is not displayed");
        Assert.assertTrue(productTotalPrice1.isDisplayed(), "First product total price is not displayed");

        Assert.assertTrue(productPrice2.isDisplayed(), "Second product price is not displayed");
        Assert.assertTrue(productQuantity2.isDisplayed(), "Second product quantity is not displayed");
        Assert.assertTrue(productTotalPrice2.isDisplayed(), "Second product total price is not displayed");
    }

    @Step("Verify that the product quantity is displayed correctly after changing it ")
    public void verifyProductQuantityInCart(int expectedQuantity) {
        WebElement prodQuantity = driver.findElement(productQuantity);
        Assert.assertEquals(prodQuantity.getText(),"4","Quantity mismatch");
    }

    @Step("Verify that cart page is displayed")
    public void VerifyCartPageDisplayed(){
        WebElement cartDisplayed = driver.findElement(cartInfo);
        Assert.assertTrue(cartDisplayed.isDisplayed(), "cart is not displayed");
    }

    @Step("Click on Proceed to checkout button")
    public ProceedToCheckPage clickOnProceedBTN(){
        driver.findElement(proceedToCheckOutBTN).click();
        return new ProceedToCheckPage(driver);
    }

    @Step("Click on register/login button")
    public SignUp_LoginPage clickOnRegister_LoginBTN(){
        driver.findElement(Register_LoginBTN).click();
        return new SignUp_LoginPage(driver);
    }

    @Step("Click on remove button")
    public void clickOnRemoveBTN(){
        wait.until(ExpectedConditions.elementToBeClickable(removeProduct1BTN)).click();
        wait.until(ExpectedConditions.elementToBeClickable(removeProduct2BTN)).click();
        wait.until(ExpectedConditions.elementToBeClickable(removeProduct3BTN)).click();
    }

    @Step("Verify that the products are removed")
    public void verifyThatProductRemoved(){
        WebElement emptyCart = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
        Assert.assertTrue(emptyCart.isDisplayed(), "Verification failed: Empty cart message is not displayed after product removal.");
    }

}
