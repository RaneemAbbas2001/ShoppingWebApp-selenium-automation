package AppPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class TestCasesPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By testCasesHeader = By.xpath("//*[@id=\"form\"]/div/div[1]/div/h2/b");

    public TestCasesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Verify that user is navigated to test cases page successfully")
    public void Verify_user_navigated_to_testCases_page_successfully(){
        WebElement testCaseDisplayed = driver.findElement(testCasesHeader);
        Assert.assertTrue(testCaseDisplayed.isDisplayed(), "Image is not displayed");
    }

}
