package practice5.poker.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import practice5.poker.classes.PokerPlayer;
import practice5.poker.interfaces.IPokerPlayer;
import practice5.poker.interfaces.pages.IEditPlayerPage;


/**
 * Created by Serhii on 30-Nov-16.
 * Player - Edit page
 */
public class EditPlayerPage implements IEditPlayerPage {

    private WebDriver driver;
    @FindBy(id = LAST_NAME_ID)
    public WebElement lastNameElement;
    @FindBy(id = FIRST_NAME_ID)
    private WebElement firstNameElement;
    @FindBy(id = COUNTRY_ID)
    private WebElement countryElement;
    @FindBy(id = ADDRESS_ID)
    private WebElement addressElement;
    @FindBy(id = EMAIL_ID)
    private WebElement emailElement;
    @FindBy(id = CITY_ID)
    private WebElement cityElement;
    @FindBy(id = PHONE_ID)
    private WebElement phoneElement;
    @FindBy(id = USERNAME_ID)
    private WebElement usernameElement;
    @FindBy(name = BUTTON_SAVE)
    private WebElement saveButton;
    @FindBy(name = BUTTON_CANCEL)
    private WebElement cancelButton;

    public EditPlayerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
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
        saveButton.click();
    }

    public void clickOnButtonCancel(){
        cancelButton.click();
    }

    //Correct errors on EDIT_FORM
    public IPokerPlayer editPokerPlayerIntoFormWithCorrectErrorsAfterInsert(IPokerPlayer pokerPlayer, String actualCountry){
        //swap LastName & FirstName
        lastNameElement.clear();
        lastNameElement.sendKeys(pokerPlayer.getFirstName());
        firstNameElement.clear();
        firstNameElement.sendKeys(pokerPlayer.getLastName());
        addressElement.sendKeys(pokerPlayer.getAddress());
        //set country from parameter @actualCountry
//        countryElement.clear();
        countryElement.sendKeys(actualCountry);
        return getPokerPlayerFromEditForm();
    }

    public IPokerPlayer editPokerPlayerIntoFormWithCorrectErrors(IPokerPlayer pokerPlayer, String actualCountry){
        editPokerPlayerIntoFormWithoutCorrectErrors(pokerPlayer);
        //swap LastName & FirstName
        lastNameElement.clear();
        lastNameElement.sendKeys(pokerPlayer.getFirstName());
        firstNameElement.clear();
        firstNameElement.sendKeys(pokerPlayer.getLastName());
        if(!addressElement.getAttribute(VALUE).equals("")) addressElement.clear();
        addressElement.sendKeys(pokerPlayer.getAddress());
        //set country from parameter @actualCountry
        countryElement.sendKeys(actualCountry);
        return getPokerPlayerFromEditForm();
    }
    
    private void editPokerPlayerIntoFormWithoutCorrectErrors(IPokerPlayer pokerPlayer){
        emailElement.clear();
        cityElement.clear();
        phoneElement.clear();
        emailElement.sendKeys(pokerPlayer.getEmail());
        cityElement.sendKeys(pokerPlayer.getCity());
        phoneElement.sendKeys(pokerPlayer.getPhone());
        countryElement.sendKeys(pokerPlayer.getCountry());//5
    }

    private void editPokerPlayerIntoFormWithoutCorrectErrorsEightField(IPokerPlayer pokerPlayer){
        firstNameElement.clear();//5
        lastNameElement.clear();
        addressElement.clear();
        firstNameElement.sendKeys(pokerPlayer.getFirstName());//5
        lastNameElement.sendKeys(pokerPlayer.getLastName());
        addressElement.sendKeys(pokerPlayer.getAddress());
        editPokerPlayerIntoFormWithoutCorrectErrors(pokerPlayer);
    }

    public IPokerPlayer getPokerPlayerFromEditForm(){
        return new PokerPlayer(
            usernameElement.getAttribute(VALUE),
            emailElement.getAttribute(VALUE),
            firstNameElement.getAttribute(VALUE),
            lastNameElement.getAttribute(VALUE),
            cityElement.getAttribute(VALUE),
            addressElement.getAttribute(VALUE),
            phoneElement.getAttribute(VALUE),
            countryElement.getAttribute(VALUE)
        );
    }

}
