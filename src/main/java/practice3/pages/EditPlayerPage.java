package practice3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import practice3.classes.Elements;
import practice3.interfaces.IElements;
import practice3.interfaces.IPokerPlayer;
import practice3.classes.PokerPlayer;

/**
 * Created by Serhii on 30-Nov-16.
 */
public class EditPlayerPage implements practice3.interfaces.pages.IEditPlayerPage {

    private WebDriver driver;

    public EditPlayerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void editPokerPlayerAndSave(PokerPlayer pokerPlayer){
        IPokerPlayer pokerPlayerFromEditForm = getPokerPlayerFromEditForm();
        editPokerPlayerAndSave(pokerPlayer, pokerPlayerFromEditForm);
    }

    public void editPokerPlayerAndSave(IPokerPlayer pokerPlayer, IPokerPlayer pokerPlayerFromEditForm){
        editPokerPlayerIntoForm(pokerPlayer, pokerPlayerFromEditForm.getCountry());
        clickOnButtonSave();
    }

    public void clickOnButtonSave(){
        driver.findElement(By.name(BUTTON_SAVE)).click();
    }

    public void clickOnButtonCancel(){
        driver.findElement(By.name(BUTTON_CANCEL)).click();
    }

    //Correct errors on EDIT_FORM
    public IPokerPlayer editPokerPlayerIntoForm(IPokerPlayer pokerPlayer, String actualCountry){

        //swap LastName & FirstName
        WebElement lastNameElement = driver.findElement(By.id(EDIT_FORM.getLastName()));
        lastNameElement.clear();
        lastNameElement.sendKeys(pokerPlayer.getFirstName());
        WebElement firstNameElement = driver.findElement(By.id(EDIT_FORM.getFirstName()));
        firstNameElement.clear();
        firstNameElement.sendKeys(pokerPlayer.getLastName());


        driver.findElement(By.id(EDIT_FORM.getAddress())).sendKeys(pokerPlayer.getAddress());


        //set country from parameter @actualCountry
        WebElement countryElement = driver.findElement(By.id(EDIT_FORM.getCountry()));
//        countryElement.clear();
        countryElement.sendKeys(actualCountry);

        return getPokerPlayerFromEditForm();
    }

    public IPokerPlayer getPokerPlayerFromEditForm(){
        return new PokerPlayer(

                driver.findElement(By.id(EDIT_FORM.getUsername())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getEmail())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getFirstName())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getLastName())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getCity())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getAddress())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getPhone())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getCountry())).getAttribute(VALUE)

        );
    }
}
