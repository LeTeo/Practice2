package practice3.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import practice3.classes.PokerPlayerSmall;
import practice3.interfaces.IPokerPlayerSmall;
import practice3.interfaces.pages.ILoginPage;
import practice3.pages.LoginPage;
import practice3.pages.PlayersPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Serhii on 30-Nov-16.
 */
public class LoginTests {

    public static final String LOGIN_GROUP = "LoginTests";

    //Test values
    public static final IPokerPlayerSmall TEST_POSITIVE_VALUE = new PokerPlayerSmall
            ("admin", "123");
    public static final IPokerPlayerSmall NEGATIVE_TEST_WRONG_PASSWORD_VALUE = new PokerPlayerSmall
            (TEST_POSITIVE_VALUE.getUsername(), PokerPlayerSmall.RandomPassword());
    public static final IPokerPlayerSmall NEGATIVE_TEST_LOGIN_EMPTY_VALUE = new PokerPlayerSmall
            ("", TEST_POSITIVE_VALUE.getPassword());
    public static final IPokerPlayerSmall NEGATIVE_TEST_PASSWORD_EMPTY_VALUE = new PokerPlayerSmall
            (TEST_POSITIVE_VALUE.getUsername(), "");
    public static final IPokerPlayerSmall NEGATIVE_TEST_EMPTY_FIELDS_VALUE = new PokerPlayerSmall
            ("", "");
    //End Test values
    private static final String CSS_EXPECTED_ERORR_MSG = "Invalid username or password";
    private static final String CSS_EXPECTED_ERORR_USERNAME_OR_PASSWORD_MSG = "Value is required and can't be empty";
    private static final String YOU_ARE_NOT_ON_LOGIN_PAGE = "You are NOT on login page";
    private static final String WRONG_TITLE_AFTER_UNSUCCESSFUL_LOGIN = "Wrong title after unsuccessful login";
    private static final String WRONG_TITLE_AFTER_LOGIN = "Wrong title after login";
    private static final String VALIDATION_ERROR_MESSAGE_IS_NOT_VALID = "Validation error message is not valid";
    private static final String YOU_ARE_STILL_ON_LOGIN_PAGE = "You are still on login page.";
    private WebDriver driver = null; // Declare var
    private ILoginPage loginPage = null;

    /**
     * Open browser and set default settings
     */
    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver(); //initialize/create object/open firefox
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * Precondition:
     * 1-2. Open login page
     */
    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
        loginPage.open(); //open poker URL
    }

    /**
     * Steps to reproduce:
     *                   username password
     *  1. Authorization "Admin", "123"
     *  2. Check TITLE
     *  3. Check Url
     */
    @Test(groups = LOGIN_GROUP)
    public void positiveTest() {
        loginPage.authorization(TEST_POSITIVE_VALUE.getUsername(), TEST_POSITIVE_VALUE.getPassword());
        Assert.assertEquals(driver.getTitle(), PlayersPage.TITLE, WRONG_TITLE_AFTER_LOGIN);
        Assert.assertNotEquals(driver.getCurrentUrl(), loginPage.URL, YOU_ARE_STILL_ON_LOGIN_PAGE);
    }

    /**
     * Steps to reproduce:
     *                   username password
     *  1. Authorization "Admin",  Random
     *  2. Check URL
     *  3. Check TITLE
     *  4. Check ERROR MESSAGE
     */
    @Test(groups = LOGIN_GROUP)
    public void negativeTestWrongPassword(){
        loginPage.authorization(NEGATIVE_TEST_WRONG_PASSWORD_VALUE);
        Assert.assertEquals(driver.getCurrentUrl(), loginPage.URL, YOU_ARE_NOT_ON_LOGIN_PAGE);
        Assert.assertEquals(driver.getTitle(), loginPage.TITLE, WRONG_TITLE_AFTER_UNSUCCESSFUL_LOGIN);
        Assert.assertEquals(loginPage.getErrorMessage(), CSS_EXPECTED_ERORR_MSG, VALIDATION_ERROR_MESSAGE_IS_NOT_VALID);
    }


    /**
     * Steps to reproduce:
     *                   username password
     *  1. Authorization    "",    "123"
     *  2. Check URL
     *  3. Check TITLE
     *  4. Check ERROR MESSAGE
     */
    @Test(groups = LOGIN_GROUP)
    public void negativeTestLoginEmpty(){
        loginPage.authorization(NEGATIVE_TEST_LOGIN_EMPTY_VALUE);
        Assert.assertEquals(driver.getCurrentUrl(), loginPage.URL, YOU_ARE_NOT_ON_LOGIN_PAGE);
        Assert.assertEquals(driver.getTitle(), loginPage.TITLE, WRONG_TITLE_AFTER_UNSUCCESSFUL_LOGIN);
        Assert.assertEquals(loginPage.getErrorMessageLogin(), CSS_EXPECTED_ERORR_USERNAME_OR_PASSWORD_MSG, VALIDATION_ERROR_MESSAGE_IS_NOT_VALID);
    }

    /**
     *  Steps to reproduce:
     *                   username password
     *  1. Authorization  "Admin",  ""
     *  2. Check URL
     *  3. Check TITLE
     *  4. Check ERROR MESSAGE
     */
    @Test(groups = LOGIN_GROUP)
    public void negativeTestPasswordEmpty(){
        loginPage.authorization(NEGATIVE_TEST_PASSWORD_EMPTY_VALUE);
        Assert.assertEquals(driver.getCurrentUrl(), loginPage.URL, YOU_ARE_NOT_ON_LOGIN_PAGE);
        Assert.assertEquals(driver.getTitle(), loginPage.TITLE, WRONG_TITLE_AFTER_UNSUCCESSFUL_LOGIN);
        Assert.assertEquals(loginPage.getErrorMessagePassword(), CSS_EXPECTED_ERORR_USERNAME_OR_PASSWORD_MSG, VALIDATION_ERROR_MESSAGE_IS_NOT_VALID);
    }

    /**
     *  Steps to reproduce:
     *                   username password
     *  1. Authorization    "",      ""
     *  2. Check URL
     *  3. Check TITLE
     *  4. Check ERROR MESSAGE for login
     *  5. Check ERROR MESSAGE for password
     */
    @Test(groups = LOGIN_GROUP)
    public void negativeTestEmptyFields(){
        loginPage.authorization(NEGATIVE_TEST_EMPTY_FIELDS_VALUE);
        Assert.assertEquals(driver.getCurrentUrl(), loginPage.URL, YOU_ARE_NOT_ON_LOGIN_PAGE);
        Assert.assertEquals(driver.getTitle(), LoginPage.TITLE, WRONG_TITLE_AFTER_UNSUCCESSFUL_LOGIN);
        Assert.assertEquals(loginPage.getErrorMessageLogin(), CSS_EXPECTED_ERORR_USERNAME_OR_PASSWORD_MSG, VALIDATION_ERROR_MESSAGE_IS_NOT_VALID);
        Assert.assertEquals(loginPage.getErrorMessagePassword(), CSS_EXPECTED_ERORR_USERNAME_OR_PASSWORD_MSG, VALIDATION_ERROR_MESSAGE_IS_NOT_VALID);
    }

    /**
     * Close browser
     */
    @AfterTest
    public void afterTest() {
        driver.quit();
    }


}
