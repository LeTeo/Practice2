package practice4.draggable.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import practice4.draggable.pages.ActionPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 07.12.2016.
 */
public class ActionTests {
    private WebDriver driver = null;
    private ActionPage actionPage = null;
    private SoftAssert softAssert = null;

    @BeforeTest
    public void beforeTest(){
        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
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
//        driver.quit();
    }
}
