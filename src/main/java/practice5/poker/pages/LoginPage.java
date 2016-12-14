package practice5.poker.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import practice5.poker.interfaces.IPokerPlayerSmall;
import practice5.poker.interfaces.pages.ILoginPage;

/**
 * Created by Serhii on 30-Nov-16.
 * Pattern PageObject for Login Page
 */
public class LoginPage implements ILoginPage {

    private WebDriver driver;
    @FindBy(id = ID_USERNAME)
    private WebElement usernameInput;
    @FindBy(id = ID_PASSWORD)
    private WebElement passwordInput;
    @FindBy(id = ID_LOGIN)
    private WebElement loginButton;
    @FindBy(xpath = XPATH_CSS_ERROR)
    private WebElement errorMessage;
    @FindBy(xpath = XPATH_CSS_USERNAME_ERROR)
    private WebElement usernameErrorMessage;
    @FindBy(xpath = XPATH_CSS_PASSWORD_ERROR)
    private WebElement passwordErrorMessage;

    public WebDriver getDriver() {
        return driver;
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void open() {
        driver.get(URL);
    }

    public void setUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username); // Set username
    }

    public void setPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password); // Set password
    }

    public void clickOnLogin() {
        loginButton.click(); // click on LogIn button
    }

    public void authorization(String login, String password) {
        setUsername(login);
        setPassword(password);
        clickOnLogin();
    }

    public void authorization(IPokerPlayerSmall pokerPlayer) {
        authorization(pokerPlayer.getUsername(),pokerPlayer.getPassword());
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }

    public String getErrorMessageLogin() {
        return usernameErrorMessage.getText();
    }
    public String getErrorMessagePassword() {
        return passwordErrorMessage.getText();
    }
}
