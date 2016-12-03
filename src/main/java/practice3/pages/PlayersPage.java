package practice3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Serhii on 30-Nov-16.
 * Players Page
 */
public class PlayersPage implements practice3.interfaces.pages.IPlayersPage {

    public static final String TITLE = "Players";
    public static final String URL = "http://80.92.229.236:81/players";
    public static final String XPATH_PLAYERS_INSERT = ".//a[@href='http://80.92.229.236:81/players/insert']";
    public static final String USERNAME_FIELD_ID = "723a925886__login";
    public static final String XPATH_INPUT_SEARCH = ".//input[@VALUE='Search']";
    public static final String XPATH_FIND_USERNAME_EDIT_OPEN = ".//tr[.//a[text()='";
    public static final String XPATH_FIND_USERNAME_EDIT_CLOSE = "']]//img[@title='Edit']";
    public static final String XPATH_FIND_USERNAME_DELETE_OPEN = ".//tr[.//a[text()='";
    public static final String XPATH_FIND_USERNAME_DELETE_CLOSE = "']]//img[@title='Delete']";
    public static final String XPATH_DELETE_SUCCESS = ".//div[@class='datagrid_flashmessagespanel_container']/ul";

    public static final String VALUE = "VALUE";
    private static final String EMAIL_FIELD_ID = "723a925886__email";
    public static final String DELETE_MESSAGE = "Player has been deleted";

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


}
