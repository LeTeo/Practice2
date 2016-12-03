package practice3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import practice3.interfaces.IPokerPlayerSmall;
import practice3.interfaces.pages.ILoginPage;

/**
 * Created by Serhii on 30-Nov-16.
 */
public class LoginPage implements practice3.interfaces.pages.ILoginPage {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }
    @Deprecated
    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickOnLogin();
    }

    public void setUsername(String username) {
        WebElement usernameInput = driver.findElement(By.id(ID_USERNAME)); // Find username input
        usernameInput.clear();
        usernameInput.sendKeys(username); // Set username
    }

    public void setPassword(String password) {
        WebElement passwordInput = driver.findElement(By.id(ILoginPage.ID_PASSWORD)); // Find password input
        passwordInput.clear();
        passwordInput.sendKeys(password); // Set password
    }

    public void clickOnLogin() {
        WebElement loginButton = driver.findElement(By.id(ID_LOGIN)); // Find login button
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
        WebElement errorMsgElement = driver.findElement(By.xpath(XPATH_CSS_ERROR));
        String errorMsg = errorMsgElement.getText();
        return errorMsg;
    }

    public String getErrorMessageLogin() {
        WebElement errorMsgElement = driver.findElement(By.xpath(XPATH_CSS_USERNAME_ERROR));
        String errorMsg = errorMsgElement.getText();
        return errorMsg;
    }
    public String getErrorMessagePassword() {
        WebElement errorMsgElement = driver.findElement(By.xpath(XPATH_CSS_PASSWORD_ERROR));
        String errorMsg = errorMsgElement.getText();
        return errorMsg;
    }
}
