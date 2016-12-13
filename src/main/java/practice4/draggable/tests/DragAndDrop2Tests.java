package practice4.draggable.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import practice4.poker.tests.*;
import practice4.draggable.pages.DragAndDrop2Page;

import java.util.concurrent.TimeUnit;

/**
 * Created by ly0w on 09.12.2016.
 * Задание на класс Actions
 */
public class DragAndDrop2Tests {

    public static WebDriver driver = null;
    private DragAndDrop2Page dragAndDrop2Page = null;
    //private SoftAssert softAssert = null;

    @BeforeSuite
    public void beforeSuite(){
        if(LoginTests.driver == null)
            driver = new FirefoxDriver();
        else if(CRUDUserTests.driver == null)
            driver = LoginTests.driver;
        else
            driver = CRUDUserTests.driver;
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        dragAndDrop2Page = new DragAndDrop2Page(driver);
        //softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void beforeMethod() {
        dragAndDrop2Page.open();
    }


    @DataProvider
    public Object[][] scenario1Data() {
        return new Object[][] {
                {"Are you sure that you want to delete?", "Wrong delete alert message", true, "Box is not exist", false, "Box still exist"},
        };
    }
    /**
     * Steps to reproduce:
     * Сценарий 1.
     * 1. Перетащить любой бокс в корзину(Trash)
     * 2. Проверить правильность сообщения о подтверждении действия.
     * 3. Нажать "Отмена"
     * 4. Проверить, что выбранный бокс не был удален.
     * 5. Перенести бокс в корзину.
     * 6. Подвердить действие.
     * 7. Проверить что бокс таки удалился.
     */
    //@Parameters({"expalmsg","erralrmsg","expbxisxst","bxntex","exbxisntex","bxstlex"})
    @Test(dataProvider = "scenario1Data")
    public void Scenario1Test(String expectedAlertMessage, String errorAlertMessage, boolean expectedBoxIsExist,
                              String boxNotExists, boolean expectedBoxIsNotExist, String boxStillExists){
        dragAndDrop2Page.randomBoxMoveToTrashAndSwitchToAlert();
        Assert.assertEquals(dragAndDrop2Page.getDeleteAlertMessage(),expectedAlertMessage,errorAlertMessage);
        dragAndDrop2Page.clickOnDeleteAlertCancelButton();
        Assert.assertEquals(dragAndDrop2Page.boxIsExists(), expectedBoxIsExist, boxNotExists);//Assert.assertEquals(dragAndDrop2Page.countBoxes(), 7, "!=7");
        dragAndDrop2Page.randomBoxMoveToTrashAndSwitchToAlert();
        dragAndDrop2Page.clickOnDeleteAlertAcceptButton();
        Assert.assertEquals(dragAndDrop2Page.boxIsExists(), expectedBoxIsNotExist, boxStillExists);//Assert.assertEquals(dragAndDrop2Page.countBoxes(), 6, "!=6");
    }

    @DataProvider
    public Object[][] scenario2Data(){
        return new Object[][] {
                {true, "Not Ascending Sortable Boxes", true, "Not Descending Sortable boxes"}
        };
    }

    /**
     * Steps to reproduce:
     * Сценарий 2.
     * 1. Отсортировать боксы по порядку (1 2 3 4 5 6 7) используя класс Actions.
     * 2. Проверить, что боксы правильно отсортированы на странице.
     * 3. Сделать обратуню сортировку (7 6 5 4 3 2 1)
     * 4. Проверить, что боксы правильно отсортированы на странице.
     * Комментарий: используйте любую сортировку или собственное решение :)
     * @param expectedIsAscendingSortable боксы правильно отсортированы на странице
     * @param notAscendingSortableBoxes сообщение, что боксы правильно отсортированы на странице
     * @param expectedIsDescendingSortable боксы правильно отсортированы на странице
     * @param notDescendingSortableBoxes сообщение, что боксы правильно отсортированы на странице
     */
    @Test(dataProvider = "scenario2Data")
    public void Scenario2Test(boolean expectedIsAscendingSortable, String notAscendingSortableBoxes,
                              boolean expectedIsDescendingSortable, String notDescendingSortableBoxes){
        dragAndDrop2Page.sortBoxesAscending();
        Assert.assertEquals(dragAndDrop2Page.boxesIsAscendingSortable(),expectedIsAscendingSortable, notAscendingSortableBoxes);
        dragAndDrop2Page.sortBoxesDescending();
        Assert.assertEquals(dragAndDrop2Page.boxesIsDescendingSortable(),expectedIsDescendingSortable, notDescendingSortableBoxes);
    }

    @AfterSuite
    public void afterSuite() {
        driver.quit();
    }
}
