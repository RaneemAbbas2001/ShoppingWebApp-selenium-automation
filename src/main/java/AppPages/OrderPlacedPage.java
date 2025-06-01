package AppPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;

public class OrderPlacedPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By deleteBTN = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a");
    private By invoiceBTN = By.xpath("//*[@id=\"form\"]/div/div/div/a");
    private By continueBTN = By.xpath("//*[@id=\"form\"]/div/div/div/div/a");
    private String downloadDirectory;

    public OrderPlacedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    @Step("Click on delete acoount button")
    public DeletedAccPage clickOnDeleteBTN(){
        WebElement Delete_accountBTN = driver.findElement(deleteBTN);
        Delete_accountBTN.click();
        return new DeletedAccPage(driver);
    }

    @Step("Click on invoice button")
    public void clickOnInvoiceBTN(){

        driver.findElement(invoiceBTN).click();
    }

    @Step("Click on continue button")
    public HomePage clickOnContinueBTN(){
        driver.findElement(continueBTN).click();
        return new HomePage(driver);
    }

    @Step("Verify that the invoice is downloaded successfully")
    public void verifyInvoiceDownloadedSuccessfully(String expectedInvoiceFileName){
        String expectedFilePath = Paths.get(downloadDirectory, expectedInvoiceFileName).toString();
        File downloadedFile = new File(expectedFilePath);
//        driver.assertThat().file(downloadedFile).exists().withCustomReportMessage("Verify that the invoice file was downloaded.").perform();
    }

}
