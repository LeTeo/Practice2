package practice3.interfaces.pages;

import org.openqa.selenium.WebDriver;
import practice3.interfaces.IPokerPlayerSmall;

/**
 * Created by ly0w on 04.12.2016.
 */
public interface ILoginPage {
    String URL = "http://80.92.229.236:81/auth/login";
    String ID_USERNAME = "username";
    String ID_PASSWORD = "password";
    String ID_LOGIN = "logIn";
    String XPATH_CSS_ERROR = "//ul[@class='errors']/li";
    String XPATH_CSS_USERNAME_ERROR = "//dd[@id='username-element']//ul[@class='errors']/li";
    String XPATH_CSS_PASSWORD_ERROR = "//dd[@id='password-element']//ul[@class='errors']/li";
    String TITLE = "Login";

    WebDriver getDriver();

    void open();

    @Deprecated
    void login(String username, String password);

    void setUsername(String username);

    void setPassword(String password);

    void clickOnLogin();

    void authorization(String login, String password);

    void authorization(IPokerPlayerSmall pokerPlayer);

    String getErrorMessage();

    String getErrorMessageLogin();

    String getErrorMessagePassword();
}
