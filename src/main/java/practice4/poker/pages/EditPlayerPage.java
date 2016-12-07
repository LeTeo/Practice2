package practice4.poker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import practice4.poker.classes.PokerPlayer;
import practice4.poker.interfaces.IPokerPlayer;
import practice4.poker.interfaces.pages.IEditPlayerPage;

/**
 * Created by Serhii on 30-Nov-16.
 * Player - Edit page
 */
public class EditPlayerPage implements IEditPlayerPage {

    private WebDriver driver;

    public EditPlayerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void editPokerPlayerAndSave(IPokerPlayer pokerPlayer){
        IPokerPlayer pokerPlayerFromEditForm = getPokerPlayerFromEditForm();
        editPokerPlayerAndSave(pokerPlayer, pokerPlayerFromEditForm);
    }

    public void editPokerPlayerAndSave(IPokerPlayer pokerPlayer, IPokerPlayer pokerPlayerFromEditForm){
        editPokerPlayerIntoFormWithCorrectErrors(pokerPlayer, pokerPlayerFromEditForm.getCountry());
        clickOnButtonSave();
    }
    public void editPokerPlayerAndSaveAfterInsert(IPokerPlayer pokerPlayer){
        IPokerPlayer pokerPlayerFromEditForm = getPokerPlayerFromEditForm();
        editPokerPlayerAndSaveAfterInsert(pokerPlayer, pokerPlayerFromEditForm);
    }

    public void editPokerPlayerAndSaveAfterInsert(IPokerPlayer pokerPlayer, IPokerPlayer pokerPlayerFromEditForm){
        editPokerPlayerIntoFormWithCorrectErrorsAfterInsert(pokerPlayer, pokerPlayerFromEditForm.getCountry());
        clickOnButtonSave();
    }

    public void editPokerPlayerAndSaveWithoutCorrectErrors(IPokerPlayer pokerPlayer){
        editPokerPlayerIntoFormWithoutCorrectErrorsEightField(pokerPlayer);
        clickOnButtonSave();
    }

    public void clickOnButtonSave(){
        driver.findElement(By.name(BUTTON_SAVE)).click();
    }

    public void clickOnButtonCancel(){
        driver.findElement(By.name(BUTTON_CANCEL)).click();
    }

    //Correct errors on EDIT_FORM
    public IPokerPlayer editPokerPlayerIntoFormWithCorrectErrorsAfterInsert(IPokerPlayer pokerPlayer, String actualCountry){
        
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

    public IPokerPlayer editPokerPlayerIntoFormWithCorrectErrors(IPokerPlayer pokerPlayer, String actualCountry){
        editPokerPlayerIntoFormWithoutCorrectErrors(pokerPlayer);
        //swap LastName & FirstName
        WebElement lastNameElement = driver.findElement(By.id(EDIT_FORM.getLastName()));
        lastNameElement.clear();
        lastNameElement.sendKeys(pokerPlayer.getFirstName());
        WebElement firstNameElement = driver.findElement(By.id(EDIT_FORM.getFirstName()));
        firstNameElement.clear();
        firstNameElement.sendKeys(pokerPlayer.getLastName());
        WebElement addressElement = driver.findElement(By.id(EDIT_FORM.getAddress()));
        if(!addressElement.getAttribute(VALUE).equals("")) addressElement.clear();
        addressElement.sendKeys(pokerPlayer.getAddress());
        //set country from parameter @actualCountry
        WebElement countryElement = driver.findElement(By.id(EDIT_FORM.getCountry()));
        countryElement.sendKeys(actualCountry);
        return getPokerPlayerFromEditForm();
    }
    
    private void editPokerPlayerIntoFormWithoutCorrectErrors(IPokerPlayer pokerPlayer){
        driver.findElement(By.id(EDIT_FORM.getEmail())).clear();
        driver.findElement(By.id(EDIT_FORM.getCity())).clear();
        driver.findElement(By.id(EDIT_FORM.getPhone())).clear();
        driver.findElement(By.id(EDIT_FORM.getEmail())).sendKeys(pokerPlayer.getEmail());
        driver.findElement(By.id(EDIT_FORM.getCity())).sendKeys(pokerPlayer.getCity());
        driver.findElement(By.id(EDIT_FORM.getPhone())).sendKeys(pokerPlayer.getPhone());
        driver.findElement(By.id(EDIT_FORM.getCountry())).sendKeys(pokerPlayer.getCountry());//5
    }

    private void editPokerPlayerIntoFormWithoutCorrectErrorsEightField(IPokerPlayer pokerPlayer){
        driver.findElement(By.id(EDIT_FORM.getFirstName())).clear();//5
        driver.findElement(By.id(EDIT_FORM.getLastName())).clear();
        driver.findElement(By.id(EDIT_FORM.getAddress())).clear();
        driver.findElement(By.id(EDIT_FORM.getFirstName())).sendKeys(pokerPlayer.getFirstName());//5
        driver.findElement(By.id(EDIT_FORM.getLastName())).sendKeys(pokerPlayer.getLastName());
        driver.findElement(By.id(EDIT_FORM.getAddress())).sendKeys(pokerPlayer.getAddress());
        editPokerPlayerIntoFormWithoutCorrectErrors(pokerPlayer);
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
