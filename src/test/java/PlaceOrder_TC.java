import AppPages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceOrder_TC {

    private WebDriver driver;
    private HomePage homePageObj;
    private CartPage cartPageObj;
    private SignUp_LoginPage Signup_loginPageObj;
    private Acc_info_Page Account_info_Obj;
    private AccCreatedPage createdAccountPageObj;
    private LoggedinAccPage loggedinAccountPageObj;
    private ProceedToCheckPage proceedToCheckOutPageObj;
    private PaymentPage paymentPageObj;
    private OrderPlacedPage orderPlacedPageObj;
    private DeletedAccPage deletedAccPageObj;
    private String name = "Akeem Melton";
    private String email = "testuser" + System.currentTimeMillis() + "@example.com"; // Unique email
    private String password = "password123";
    private String day = "1";
    private String month = "January";
    private String year = "2000";
    private String firstName = "Test";
    private String lastName = "User";
    private String company = "Test Company";
    private String address = "123 Test Address";
    private String address2 = "Apt 4B";
    private String country = "Canada";
    private String state = "Ontario";
    private String city = "Toronto";
    private String zipcode = "M5V 2H1";
    private String mobileNumber = "1234567890";
    private String nameOnCard = "User";
    private String cardNumber = "4111111111111111";
    private String cvc = "123";
    private String expiryMonth = "12";
    private String expiryYear = "2025";
    private String CorrectEmail = "suzo@mailinator.com";
    private String CorrectPassword = "e4@b8jB6zU5LcgP";
    private String expectedInvoiceFileName = "invoice.txt";


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
    public void TestCase14_PlaceOrderRegisterWhileCheckout() throws InterruptedException {
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();
        homePageObj.scrollDownToFeature();
        homePageObj.addProducts();

        cartPageObj = homePageObj.clickCartBTN();
        cartPageObj.VerifyCartPageDisplayed();
        cartPageObj.clickOnProceedBTN();

        Signup_loginPageObj = cartPageObj.clickOnRegister_LoginBTN();
        Account_info_Obj = Signup_loginPageObj.fillSignupDetails(name, email);
        Account_info_Obj.fillAccountDetails(password,day,month,year);
        Account_info_Obj.selectNewsLetterAndOffers();
        Account_info_Obj.fillAddressDetails(firstName,lastName,company,address,address2,country,state,city,zipcode,mobileNumber);

        createdAccountPageObj = Account_info_Obj.clickCreateAccountButton();

        loggedinAccountPageObj = createdAccountPageObj.clickContinueButton();
        loggedinAccountPageObj.verifyLoggedInAsVisible(name);

        cartPageObj = loggedinAccountPageObj.clickOnCartBTN();

        proceedToCheckOutPageObj = cartPageObj.clickOnProceedBTN();
        proceedToCheckOutPageObj.VerifyAddressDetails_ReviewYourOrder();
        proceedToCheckOutPageObj.scrollToCommentArea_WriteComment();

        paymentPageObj = proceedToCheckOutPageObj.clickOnPlaceOrder();
        paymentPageObj.fillPaymentDetails(nameOnCard ,cardNumber,cvc,expiryMonth,expiryYear);

        orderPlacedPageObj = paymentPageObj.clickOnPayAndConfirmOrder();
        //paymentPageObj.verifyOrderSuccessMessageVisible();

        deletedAccPageObj = orderPlacedPageObj.clickOnDeleteBTN();
        deletedAccPageObj.verifyAccountDeletedVisible();
        deletedAccPageObj.clickContinueButton();
    }

    @Test
    public void TestCase15_PlaceOrderRegisterBeforeCheckout(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        Signup_loginPageObj = homePageObj.clickSignupLoginButton();
        Account_info_Obj = Signup_loginPageObj.fillSignupDetails(name, email);
        Account_info_Obj.fillAccountDetails(password,day,month,year);
        Account_info_Obj.selectNewsLetterAndOffers();
        Account_info_Obj.fillAddressDetails(firstName,lastName,company,address,address2,country,state,city,zipcode,mobileNumber);

        createdAccountPageObj = Account_info_Obj.clickCreateAccountButton();

        loggedinAccountPageObj = createdAccountPageObj.clickContinueButton();
        loggedinAccountPageObj.verifyLoggedInAsVisible(name);

        homePageObj.addProducts();
        cartPageObj = loggedinAccountPageObj.clickOnCartBTN();
        cartPageObj.VerifyCartPageDisplayed();

        proceedToCheckOutPageObj = cartPageObj.clickOnProceedBTN();
        proceedToCheckOutPageObj.scrollToCommentArea_WriteComment();

        paymentPageObj = proceedToCheckOutPageObj.clickOnPlaceOrder();
        paymentPageObj.fillPaymentDetails(nameOnCard ,cardNumber,cvc,expiryMonth,expiryYear);

        orderPlacedPageObj = paymentPageObj.clickOnPayAndConfirmOrder();
//      paymentPageObj.verifyOrderSuccessMessageVisible();

        deletedAccPageObj = orderPlacedPageObj.clickOnDeleteBTN();
        deletedAccPageObj.verifyAccountDeletedVisible();
        deletedAccPageObj.clickContinueButton();
    }

    @Test
    public void TestCase16_PlaceOrderLoginBeforeCheckout(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        Signup_loginPageObj = homePageObj.clickSignupLoginButton();
        loggedinAccountPageObj = Signup_loginPageObj.fillLoginDetails(CorrectEmail,CorrectPassword);
        loggedinAccountPageObj.verifyLoggedInAsVisible(name);

        homePageObj.addProducts();
        cartPageObj = loggedinAccountPageObj.clickOnCartBTN();
        cartPageObj.VerifyCartPageDisplayed();

        proceedToCheckOutPageObj = cartPageObj.clickOnProceedBTN();
        proceedToCheckOutPageObj.VerifyAddressDetails_ReviewYourOrder();
        proceedToCheckOutPageObj.scrollToCommentArea_WriteComment();

        paymentPageObj = proceedToCheckOutPageObj.clickOnPlaceOrder();
        paymentPageObj.fillPaymentDetails(nameOnCard ,cardNumber,cvc,expiryMonth,expiryYear);

        orderPlacedPageObj = paymentPageObj.clickOnPayAndConfirmOrder();
//      paymentPageObj.verifyOrderSuccessMessageVisible();

        deletedAccPageObj = orderPlacedPageObj.clickOnDeleteBTN();
        deletedAccPageObj.verifyAccountDeletedVisible();
        deletedAccPageObj.clickContinueButton();
    }

    @Test
    public void TestCase23_VerifyAddressDetailsCheckoutPage(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();

        Signup_loginPageObj = homePageObj.clickSignupLoginButton();
        Account_info_Obj = Signup_loginPageObj.fillSignupDetails(name, email);
        Account_info_Obj.fillAccountDetails(password,day,month,year);
        Account_info_Obj.selectNewsLetterAndOffers();
        Account_info_Obj.fillAddressDetails(firstName,lastName,company,address,address2,country,state,city,zipcode,mobileNumber);

        createdAccountPageObj = Account_info_Obj.clickCreateAccountButton();

        loggedinAccountPageObj = createdAccountPageObj.clickContinueButton();
        loggedinAccountPageObj.verifyLoggedInAsVisible(name);

        homePageObj.addProducts();
        cartPageObj = loggedinAccountPageObj.clickOnCartBTN();
        cartPageObj.VerifyCartPageDisplayed();

        proceedToCheckOutPageObj = cartPageObj.clickOnProceedBTN();
        proceedToCheckOutPageObj.VerifyTheDeliveryAddressSameAddressFilledAtRegistration(address,address2);
        proceedToCheckOutPageObj.VerifyBillingAddressSameAddressFilledAtRegistration(address,address2);

        deletedAccPageObj = proceedToCheckOutPageObj.clickOnDeleteBTN();
        deletedAccPageObj.verifyAccountDeletedVisible();
        deletedAccPageObj.clickContinueButton();
    }

    @Test
    public void TestCase24_DownloadInvoiceAfterPurchaseOrder(){
        homePageObj.navigateToHomePage();
        homePageObj.verifyHomePageVisible();
        homePageObj.scrollToCategoryLeftSideBar();
        homePageObj.addProducts();

        cartPageObj = homePageObj.clickCartBTN();
        cartPageObj.VerifyCartPageDisplayed();
        cartPageObj.clickOnProceedBTN();

        Signup_loginPageObj = cartPageObj.clickOnRegister_LoginBTN();
        Account_info_Obj = Signup_loginPageObj.fillSignupDetails(name, email);
        Account_info_Obj.fillAccountDetails(password,day,month,year);
        Account_info_Obj.selectNewsLetterAndOffers();
        Account_info_Obj.fillAddressDetails(firstName,lastName,company,address,address2,country,state,city,zipcode,mobileNumber);

        createdAccountPageObj = Account_info_Obj.clickCreateAccountButton();

        loggedinAccountPageObj = createdAccountPageObj.clickContinueButton();
        loggedinAccountPageObj.verifyLoggedInAsVisible(name);

        cartPageObj = loggedinAccountPageObj.clickOnCartBTN();
        proceedToCheckOutPageObj = cartPageObj.clickOnProceedBTN();
        proceedToCheckOutPageObj.VerifyAddressDetails_ReviewYourOrder();
        proceedToCheckOutPageObj.scrollToCommentArea_WriteComment();

        paymentPageObj = proceedToCheckOutPageObj.clickOnPlaceOrder();
        paymentPageObj.fillPaymentDetails(nameOnCard , cardNumber , cvc , expiryMonth , expiryYear);

        orderPlacedPageObj = paymentPageObj.clickOnPayAndConfirmOrder();
        orderPlacedPageObj.clickOnInvoiceBTN();
        homePageObj = orderPlacedPageObj.clickOnContinueBTN();

        deletedAccPageObj = orderPlacedPageObj.clickOnDeleteBTN();
        deletedAccPageObj.verifyAccountDeletedVisible();
    }


}
