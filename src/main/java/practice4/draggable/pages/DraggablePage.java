package practice4.draggable.pages;

import org.apache.bcel.verifier.structurals.Frame;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Admin on 07.12.2016.
 */
public class DraggablePage {

    public final static String URL = "https://jqueryui.com/draggable/";

    @FindBy
    private WebElement drag;

    @FindBy(id = "draggable")
    private WebElement drop;

    @FindBy(className = "demo-frame")
    private Frame frame;

    private WebDriver driver;

    public DraggablePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(){
        driver.get(URL);
    }

    public void dragAndDrop(){

    }

    public void switchFrame(){

    }
}
