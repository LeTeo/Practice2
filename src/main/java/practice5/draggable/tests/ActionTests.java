package practice5.draggable.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import practice5.draggable.pages.ActionPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 07.12.2016.
 * Example on practice 4
 */
public class ActionTests {
    private WebDriver driver = null;
    private ActionPage actionPage = null;
    private SoftAssert softAssert = null;

    @BeforeTest
    public void beforeTest(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        actionPage = new ActionPage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void DragAndDropTest(){
        /*todo
        1. Open page.
        2. Perform drag and drop.
        2. Check text and class attribute.
         */
        actionPage.open();
        actionPage.switchToFrame();
        actionPage.dragAndDrop();
        softAssert.assertTrue(actionPage.isDropped(), "Drag and drop failed.");
        softAssert.assertEquals(actionPage.getTargetText(), "Dropped!", "Wrong text in target.");
        //actionPage.switchToMainPage();
        softAssert.assertAll();
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
