package practice4.draggable.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import practice4.draggable.pages.DraggablePage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 07.12.2016.
 */
public class DraggableTests {
    private WebDriver driver;
    private DraggablePage draggablePage;
    private SoftAssert softAssert;

    @BeforeTest
    public void beforeTest(){
        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

    }

    @Test
    public void DragAndDropTest(){

    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
