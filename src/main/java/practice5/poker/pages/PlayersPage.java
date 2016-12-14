package practice5.poker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import practice5.poker.classes.PokerPlayer;
import practice5.poker.interfaces.IPokerPlayer;
import practice5.poker.interfaces.pages.IEditPlayerPage;
import practice5.poker.interfaces.pages.IPlayersPage;

/**
 * Created by Serhii on 30-Nov-16.
 * Players Page
 */
public class PlayersPage implements IPlayersPage {

    private WebDriver driver;
    @FindBy(id = USERNAME_FIELD_ID)
    private WebElement usernameField;
    @FindBy(id = EMAIL_FIELD_ID)
    private WebElement emailField;
    @FindBy(xpath = XPATH_PLAYERS_INSERT)
    private WebElement insertButton;
    @FindBy(xpath = XPATH_BUTTON_RESET)
    private WebElement resetButton;
    @FindBy(xpath = XPATH_INPUT_SEARCH)
    private WebElement searchButton;
    @FindBy(xpath = XPATH_DELETE_SUCCESS)
    private WebElement deleteMessage;
    @FindBy(xpath = XPATH_EMAIL_TOP)
    private WebElement emailTop;
    @FindBy(xpath = XPATH_USERNAME_TOP)
    private WebElement usernameTop;

    public void open() {
        driver.get(URL);
    }

    public PlayersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickOnInsertButton(){
        insertButton.click();
    }

    public void clickOnResetButton() {
        resetButton.click();
    }

    public void clickOnEditButton(String username){
        openEditFormByUsername(username);
    }

    public void findPlayerByUsername(String username){
        if(!usernameField.getAttribute(VALUE).equals("")) usernameField.clear();
            usernameField.sendKeys(username);
        searchButton.click();
    }

    public void openEditFormByUsername(String username) {
        driver.findElement(By.xpath(XPATH_FIND_USERNAME_EDIT_OPEN
                + username + XPATH_FIND_USERNAME_EDIT_CLOSE)).click();
    }

    public void clickOnDeleteButton(String username) {
        driver.findElement(By.xpath(XPATH_FIND_USERNAME_DELETE_OPEN
                + username + XPATH_FIND_USERNAME_DELETE_CLOSE)).click();
    }

    public void findPlayerByUsernameAndEmail(String username, String email) {
        if(!usernameField.getAttribute(VALUE).equals("")) usernameField.clear();
        usernameField.sendKeys(username);
        if(!emailField.getAttribute(VALUE).equals("")) emailField.clear();
        emailField.sendKeys(email);
        searchButton.click();
    }

    public String getDeleteMessage() {
        return deleteMessage.getText();
    }

    public IPokerPlayer insertPlayerClone(PlayersPage playersPage, IPokerPlayer pokerPlayer, IEditPlayerPage editPlayerPage) {
        //playersPage.clickOnInsertButton();
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        playersPage.openEditFormByUsername(pokerPlayer.getUsername());
        IPokerPlayer pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        editPlayerPage.editPokerPlayerAndSave(pokerPlayer,pokerPlayerFromEditForm);
        playersPage.openEditFormByUsername(pokerPlayer.getUsername());
        pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        return pokerPlayerFromEditForm;
    }

    public IPokerPlayer findUserPokerPlayerNameAndEmailLastCreatedUserById() {
        //driver.findElement(By.xpath(XPATH_PERSON_ID_SORT_ASC_CLICK)).click();
        driver.get(URL_SORT_PLAYERS_BY_INDEX_DESC);
        IPokerPlayer pokerPlayerTopFind = new PokerPlayer();
        pokerPlayerTopFind.setUsername(getTopUsername());
        pokerPlayerTopFind.setEmail(getTopEmail());
        return pokerPlayerTopFind;
    }

    public void findPlayerByUsernameAndEmail(IPokerPlayer readPokerPlayer) {
        findPlayerByUsernameAndEmail(readPokerPlayer.getUsername(),readPokerPlayer.getEmail());
    }

    private String getTopEmail() {
        return emailTop.getText();
    }

    private String getTopUsername() {
        return usernameTop.getText();
    }
}
