package practice4.draggable.pages;

import org.apache.bcel.verifier.structurals.Frame;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Admin on 07.12.2016.
 * Example on practice 4
 */
public class ActionPage {

    public final static String URL = "https://jqueryui.com/droppable/";

    @FindBy(id = "draggable")
    private WebElement sourceElement;

    @FindBy(id = "droppable")
    private WebElement targetElement;

    @FindBy(className = "demo-frame")
    private Frame frame;

    private WebDriver driver;

    public ActionPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(){
        driver.get(URL);
    }

    public void dragAndDrop(){
        Actions action = new Actions(driver);
        action.dragAndDrop(sourceElement, targetElement)
                .perform();
    }

    public boolean isDropped() {
        return targetElement.getAttribute("class").contains("ui-state-highlight");
    }

    public String getTargetText() {
        return targetElement.findElement(By.xpath(".//p")).getText();
    }

    public void switchToFrame() {
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
    }

    public void switchToMainPage() {
        driver.switchTo().defaultContent();
    }

}
