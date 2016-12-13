package practice4.poker.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import practice4.draggable.tests.DragAndDrop2Tests;
import practice4.poker.classes.CRUDPokerPlayer;
import practice4.poker.classes.PokerPlayerSmall;
import practice4.poker.interfaces.IPokerPlayerSmall;
import practice4.poker.interfaces.pages.ILoginPage;
import practice4.poker.pages.LoginPage;
import practice4.poker.pages.PlayersPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Serhii on 30-Nov-16.
 * Login Test with data provider
 */
public class LoginTests {

    @SuppressWarnings("WeakerAccess")
    public static final String NEGATIVE_LOGIN_GROUP = "NegativeLoginTests";

    //Test values
    @SuppressWarnings("WeakerAccess")
    public static final IPokerPlayerSmall TEST_POSITIVE_VALUE = new PokerPlayerSmall
            ("admin", "123");

    //End Test values
    //private static final String CSS_EXPECTED_ERORR_MSG = "Invalid username or password";
    private static final String CSS_EXPECTED_ERORR_USERNAME_OR_PASSWORD_MSG = "Value is required and can't be empty";
    private static final String YOU_ARE_NOT_ON_LOGIN_PAGE = "You are NOT on login page";
    private static final String WRONG_TITLE_AFTER_UNSUCCESSFUL_LOGIN = "Wrong title after unsuccessful login";
    private static final String WRONG_TITLE_AFTER_LOGIN = "Wrong title after login";
    private static final String VALIDATION_ERROR_MESSAGE_IS_NOT_VALID = "Validation error message is not valid";
    private static final String YOU_ARE_STILL_ON_LOGIN_PAGE = "You are still on login page.";
    public static WebDriver driver = null; // Declare var
    private ILoginPage loginPage = null;
    private SoftAssert softAssert = null;

    /**
     * Open browser and set default settings
     */
    @BeforeSuite
    public void beforeSuite() {
        if(CRUDUserTests.driver == null)
            driver = new FirefoxDriver();
        else if(DragAndDrop2Tests.driver == null)
            driver = CRUDUserTests.driver;
        else
            driver = DragAndDrop2Tests.driver;
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    /**
     * Precondition:
     * 1-2. Open login page
     */
    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
        loginPage.open(); //open poker URL
        softAssert = new SoftAssert();
    }

    @DataProvider
    Object [][] wrongLoginData(){
        return new Object[][]{
                {new PokerPlayerSmall(TEST_POSITIVE_VALUE.getUsername(), PokerPlayerSmall.RandomPassword())},
                {new PokerPlayerSmall(TEST_POSITIVE_VALUE.getUsername(), PokerPlayerSmall.RandomPassword())},
                {new PokerPlayerSmall("", TEST_POSITIVE_VALUE.getPassword())},
                {new PokerPlayerSmall(TEST_POSITIVE_VALUE.getUsername(), "")},
                {new PokerPlayerSmall("", "")},
                {new PokerPlayerSmall(PokerPlayerSmall.RandomUsername(), "")},
                {CRUDPokerPlayer.randomPokerPlayerSmall()},
        };
    }

    /**
     * Steps to reproduce:
     *  1. Authorization with username and password from DataProvider wrongDataProvider
     *  2. Check Url
     *  3. Check Title
     *  4-5. Check username if username not empty
     *  5-6. Check password if password not empty
     */
    @Test(groups = NEGATIVE_LOGIN_GROUP, dataProvider = "wrongLoginData", alwaysRun = true)
    public void negativeTest(IPokerPlayerSmall pokerPlayerSmall){
        loginPage.authorization(pokerPlayerSmall);
        softAssert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, YOU_ARE_NOT_ON_LOGIN_PAGE);
        softAssert.assertEquals(driver.getTitle(), LoginPage.TITLE, WRONG_TITLE_AFTER_UNSUCCESSFUL_LOGIN);
        if (pokerPlayerSmall.getUsername().equals(""))
            softAssert.assertEquals(loginPage.getErrorMessageLogin(), CSS_EXPECTED_ERORR_USERNAME_OR_PASSWORD_MSG, VALIDATION_ERROR_MESSAGE_IS_NOT_VALID);
        if (pokerPlayerSmall.getPassword().equals(""))
            softAssert.assertEquals(loginPage.getErrorMessagePassword(), CSS_EXPECTED_ERORR_USERNAME_OR_PASSWORD_MSG, VALIDATION_ERROR_MESSAGE_IS_NOT_VALID);
        softAssert.assertAll();
    }

    /**
     * Steps to reproduce:
     *                   username password
     *  1. Authorization "Admin", "123"
     *  2. Check TITLE
     *  3. Check Url
     */
    @SuppressWarnings("groupsTestNG")
    @Test(dependsOnGroups = NEGATIVE_LOGIN_GROUP)
    public void positiveTest() {
        loginPage.authorization(TEST_POSITIVE_VALUE.getUsername(), TEST_POSITIVE_VALUE.getPassword());
        softAssert.assertEquals(driver.getTitle(), PlayersPage.TITLE, WRONG_TITLE_AFTER_LOGIN);
        softAssert.assertNotEquals(driver.getCurrentUrl(), LoginPage.URL, YOU_ARE_STILL_ON_LOGIN_PAGE);
        softAssert.assertAll();
    }

    /**
     * Steps to reproduce:
     *                   username password
     *  1. Authorization "Admin", "123"
     *  2. Check TITLE
     *  3. Check Url
     */
    @SuppressWarnings("groupsTestNG")
    @Test(dependsOnGroups = NEGATIVE_LOGIN_GROUP)
    @Parameters({"usrn","pswrd"})
    public void positiveTest(String username, String password) {
        loginPage.authorization(username, password);
        softAssert.assertEquals(driver.getTitle(), PlayersPage.TITLE, WRONG_TITLE_AFTER_LOGIN);
        softAssert.assertNotEquals(driver.getCurrentUrl(), LoginPage.URL, YOU_ARE_STILL_ON_LOGIN_PAGE);
        softAssert.assertAll();
    }

    /**
     * Close browser
     */
    @AfterSuite
    public void afterSuite() {
        driver.quit();
    }

}
