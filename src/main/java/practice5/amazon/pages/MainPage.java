package practice5.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import practice5.amazon.pages.fragments.FooterFragment;
import practice5.amazon.pages.fragments.HeaderFragment;

/**
 * Created by Admin on 14.12.2016.
 */
public class MainPage {

    public static final String URL = "https://www.amazon.com/";
    private static WebDriver driver;
    private HeaderFragment headerFragment;
    private FooterFragment footerFragment;

    public MainPage(WebDriver driver)
    {
        driver = new FirefoxDriver();
        PageFactory.initElements(driver,this);
    }
}
