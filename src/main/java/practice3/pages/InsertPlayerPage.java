package practice3.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import practice3.classes.Elements;
import practice3.interfaces.IElements;
import practice3.interfaces.IPokerPlayer;
import practice3.classes.PokerPlayer;

/**
 * Created by admin on 02.12.2016.
 */
public class InsertPlayerPage implements practice3.interfaces.pages.IInsertPlayerPage {

    private WebDriver driver;

    public IPokerPlayer insertRandomPokerPlayer(){
        IPokerPlayer pokerPlayer = createRandomPokerPlayer();
        return insertPokerPlayer(pokerPlayer);
    }

    public IPokerPlayer insertPokerPlayer(IPokerPlayer pokerPlayer){
        insertPokerPlayerIntoForm(pokerPlayer);
        clickOnButtonSave();
        return pokerPlayer;
    }

    public IPokerPlayer insertRandomPokerPlayerAndClickButtonCancel(){
        IPokerPlayer pokerPlayer = createRandomPokerPlayer();
        return insertPokerPlayerAndClickButtonCancel(pokerPlayer);
    }

    public IPokerPlayer insertPokerPlayerAndClickButtonCancel(IPokerPlayer pokerPlayer){
        insertPokerPlayerIntoForm(pokerPlayer);
        clickOnButtonCancel();
        return pokerPlayer;
    }



    public void clickOnButtonSave(){
        driver.findElement(By.name(BUTTON_SAVE)).click();
    }

    public void clickOnButtonCancel(){
        driver.findElement(By.name(BUTTON_CANCEL)).click();

    }

    public InsertPlayerPage(WebDriver driver) {
        this.driver = driver;
    }

    public static IPokerPlayer createRandomPokerPlayer(){
        return new PokerPlayer().RandomFields();
    }

    //insert PokerPlayer fields into form
    public IPokerPlayer insertPokerPlayerIntoForm(IPokerPlayer pokerPlayer){
        driver.findElement(By.id(INSERT_FORM.getUsername())).sendKeys(pokerPlayer.getUsername());
        driver.findElement(By.id(INSERT_FORM.getPassword())).sendKeys(pokerPlayer.getPassword());
        driver.findElement(By.id(INSERT_FORM.getConfirmPassword())).sendKeys(pokerPlayer.getConfirmPassword());
        driver.findElement(By.id(INSERT_FORM.getEmail())).sendKeys(pokerPlayer.getEmail());
        driver.findElement(By.id(INSERT_FORM.getFirstName())).sendKeys(pokerPlayer.getFirstName());//5
        driver.findElement(By.id(INSERT_FORM.getLastName())).sendKeys(pokerPlayer.getLastName());
        driver.findElement(By.id(INSERT_FORM.getCity())).sendKeys(pokerPlayer.getCity());
        driver.findElement(By.id(INSERT_FORM.getAddress())).sendKeys(pokerPlayer.getAddress());
        driver.findElement(By.id(INSERT_FORM.getPhone())).sendKeys(pokerPlayer.getPhone());
        driver.findElement(By.id(INSERT_FORM.getCountry())).sendKeys(pokerPlayer.getCountry());//5

        return getPokerPlayerFromInsertedForm();
    }

    public IPokerPlayer getPokerPlayerFromInsertedForm(){

        return new PokerPlayer(
                driver.findElement(By.id(INSERT_FORM.getUsername())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getPassword())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getConfirmPassword())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getEmail())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getFirstName())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getLastName())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getCity())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getAddress())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getPhone())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getCountry())).getAttribute(VALUE)
        );
    }
}
