package practice3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import practice3.classes.PokerPlayer;
import practice3.interfaces.IPokerPlayer;
import practice3.interfaces.pages.IEditPlayerPage;

/**
 * Created by Serhii on 30-Nov-16.
 * Players Page
 */
public class PlayersPage implements practice3.interfaces.pages.IPlayersPage {

    private static final String EMAIL_FIELD_ID = "723a925886__email";
    private static final String URL_SORT_PLAYERS_BY_INDEX_DESC = "http://80.92.229.236:81/players/index/sort/us_person_id/order/DESC";

    private WebDriver driver;

    public void open() {
        driver.get(URL);
    }

    public PlayersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnInsertButton(){
        driver.findElement(By.xpath(XPATH_PLAYERS_INSERT)).click();
    }

    public void clickOnResetButton() {
        driver.findElement(By.xpath(XPATH_BUTTON_RESET)).click();
    }

    public void clickOnEditButton(String username){
        openEditFormByUsername(username);
    }


    public void findPlayerByUsername(String username){
        WebElement usernameField = driver.findElement(By.id(USERNAME_FIELD_ID));
        if(!usernameField.getAttribute(VALUE).equals("")) usernameField.clear();
            usernameField.sendKeys(username);
        driver.findElement(By.xpath(XPATH_INPUT_SEARCH)).click();
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
        WebElement usernameField = driver.findElement(By.id(USERNAME_FIELD_ID));
        if(!usernameField.getAttribute(VALUE).equals("")) usernameField.clear();
        usernameField.sendKeys(username);
        WebElement emailField = driver.findElement(By.id(EMAIL_FIELD_ID));
        if(!emailField.getAttribute(VALUE).equals("")) emailField.clear();
        emailField.sendKeys(email);
        driver.findElement(By.xpath(XPATH_INPUT_SEARCH)).click();
    }


    public String getDeleteMessage() {

        return driver.findElement(By.xpath(XPATH_DELETE_SUCCESS)).getText();
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

        return driver.findElement(By.xpath(XPATH_EMAIL_TOP)).getText();
    }

    private String getTopUsername() {
        return driver.findElement(By.xpath(XPATH_USERNAME_TOP)).getText();
    }
}
