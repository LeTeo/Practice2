package practice5.amazon.pages.fragments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Admin on 14.12.2016.
 */
public class HeaderFragment {

    private static WebDriver driver;

    public HeaderFragment(WebDriver driver)
    {
        driver = new FirefoxDriver();
        PageFactory.initElements(driver,this);
    }
}
