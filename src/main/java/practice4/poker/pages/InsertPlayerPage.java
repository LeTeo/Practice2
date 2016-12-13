package practice4.poker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import practice4.poker.classes.PokerPlayer;
import practice4.poker.interfaces.IPokerPlayer;
import practice4.poker.interfaces.pages.IInsertPlayerPage;

/**
 * Created by admin on 02.12.2016.
 * Players - Insert Page
 */
public class InsertPlayerPage implements IInsertPlayerPage {

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
    @FindBy(id = PASSWORD_ID)
    private WebElement passwordElement;
    @FindBy(id = CONFIRM_PASSWORD_ID)
    private WebElement confirmPasswordElement;
    @FindBy(name = BUTTON_SAVE)
    private WebElement saveButton;
    @FindBy(name = BUTTON_CANCEL)
    private WebElement cancelButton;

    public InsertPlayerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

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
        saveButton.click();
    }

    public void clickOnButtonCancel(){
        cancelButton.click();
    }

    private IPokerPlayer createRandomPokerPlayer(){
        return new PokerPlayer().RandomFields();
    }

    //insert PokerPlayer fields into form
    public IPokerPlayer insertPokerPlayerIntoForm(IPokerPlayer pokerPlayer){
        usernameElement.sendKeys(pokerPlayer.getUsername());
        passwordElement.sendKeys(pokerPlayer.getPassword());
        confirmPasswordElement.sendKeys(pokerPlayer.getConfirmPassword());
        emailElement.sendKeys(pokerPlayer.getEmail());
        firstNameElement.sendKeys(pokerPlayer.getFirstName());//5
        lastNameElement.sendKeys(pokerPlayer.getLastName());
        cityElement.sendKeys(pokerPlayer.getCity());
        addressElement.sendKeys(pokerPlayer.getAddress());
        phoneElement.sendKeys(pokerPlayer.getPhone());
        countryElement.sendKeys(pokerPlayer.getCountry());//5
        return getPokerPlayerFromInsertedForm();
    }

    public IPokerPlayer getPokerPlayerFromInsertedForm(){
        return new PokerPlayer(
                usernameElement.getAttribute(VALUE),
                passwordElement.getAttribute(VALUE),
                confirmPasswordElement.getAttribute(VALUE),
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
