package practice4.draggable.pages;

import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ly0w on 09.12.2016.
 * PageObject Задание на класс Actions
 */
public class DragAndDrop2Page {
    private static final String PATH_TO_FILE_IN_PROJECT = "drag_and_drop2//index.html";

    @FindBy(xpath = ".//li[@class='ui-state-default']")
    private List<WebElement> boxElements;

    @FindBy(xpath = ".//div[@id='drop' and contains(@class,'ui-droppable')]")
    private WebElement trashElement;

    private int randomNumberBox;
    private String randomValueBox;
    private Alert alert;
    private Actions actions;
    private WebDriver driver;

    public DragAndDrop2Page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public void open(){
        driver.get(new File(PATH_TO_FILE_IN_PROJECT).getAbsolutePath());
    }

    public void randomBoxMoveToTrashAndSwitchToAlert() {
        randomNumberBox = RandomUtils.nextInt(0,6);
        WebElement source = boxElements.get(randomNumberBox);
        randomValueBox = source.getText();
        actions.dragAndDrop(source, trashElement).perform();
        switchToAlert();
    }

    public void clickOnDeleteAlertCancelButton() {
        alert.dismiss();
    }

    public String getDeleteAlertMessage() {
        return alert.getText();
    }

    public void clickOnDeleteAlertAcceptButton() {
        alert.accept();
    }

    public void sortBoxesAscending() {
        Collections.sort(boxElements, new Comparator<WebElement>() {
            public int compare(WebElement o1, WebElement o2) {
                int compare = Integer.parseInt(o1.getText()) - Integer.parseInt(o2.getText());
                if(compare < 0)
                    actions.dragAndDrop(o1, o2).perform();
                return compare;
            }
        });
    }


    public void sortBoxesDescending() {
        Collections.sort(boxElements, new Comparator<WebElement>() {
            public int compare(WebElement o1, WebElement o2) {
                int compare = Integer.parseInt(o2.getText()) - Integer.parseInt(o1.getText());
                if(compare < 0)
                    actions.dragAndDrop(o1,o2).perform();
                return compare;
            }
        });
    }

    public boolean boxIsExists() {
        return boxElements.get(randomNumberBox).getText().equals(randomValueBox);
    }

    public boolean boxesIsAscendingSortable() {
        WebElement prev = null;
        for( WebElement elem : boxElements ) {
            if (prev != null) {
                int compare = Integer.parseInt(elem.getText()) - Integer.parseInt(prev.getText());
                if (compare < 0) {
                    return false;
                }
            }
            prev = elem;
        }
        return true;
    }

    public boolean boxesIsDescendingSortable() {
        WebElement prev = null;
        for( WebElement elem : boxElements ) {
            if (prev != null) {
                int compare = Integer.parseInt(prev.getText()) - Integer.parseInt(elem.getText());
                if (compare < 0) {
                    return false;
                }
            }
            prev = elem;
        }
        return true;
    }

    private void switchToAlert(){
        alert = driver.switchTo().alert();
    }
}
