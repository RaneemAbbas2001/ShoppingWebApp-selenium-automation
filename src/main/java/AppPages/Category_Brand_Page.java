package AppPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Category_Brand_Page {

    private WebDriver driver;
    private WebDriverWait wait;
    private By category_brand_Header = By.xpath("/html/body/section/div/div[2]/div[2]/div/h2");
    private By menBTN = By.xpath("//*[@id=\"accordian\"]/div[2]/div[1]/h4/a");
    private By menSubBTN = By.xpath("//*[@id=\"Men\"]/div/ul/li[1]/a");

    public Category_Brand_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Verify that category page is visible")
    public void verifyCategoryPageVisible(String expectedText) {
        WebElement categoryDisplayed = driver.findElement(category_brand_Header);
        Assert.assertTrue(categoryDisplayed.isDisplayed(), "Category section is not displayed");
    }

    @Step("Click on Men button at the left side bar of category side bar")
    public void clickOnMenBTN() {

        driver.findElement(menBTN);
    }

    @Step("Click on sub Men button at the left side bar of category side bar")
    public void clickOnSubMenBTN() {

        driver.findElement(menSubBTN);
    }

    @Step("Verify that category page with specific category is visible")
    public void verifyCategoryPage(String expectedText) {
        WebElement categoryPageDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ol/li[contains(text(),'Women')]")));
        Assert.assertTrue(categoryPageDisplayed.isDisplayed(), "Category page is not displayed");
    }

    @Step("Verify that brand page with specific brand is visible")
    public void verifyBrandPage(String expectedText) {
        WebElement brandHeaderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(category_brand_Header));
        Assert.assertTrue(brandHeaderElement.isDisplayed(), "Verify brand page is displayed - FAILED. Header not visible.");
        System.out.println("Verification successful: Brand page is displayed.");

        String actualText = brandHeaderElement.getText().trim();
        Assert.assertEquals(actualText, expectedText, "Verify brand text is '" + expectedText + "' - FAILED. Actual text was '" + actualText + "'.");
        System.out.println("Verification successful: Brand text is '" + expectedText + "'.");
    }
}
