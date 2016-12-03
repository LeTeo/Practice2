package practice3.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import practice3.interfaces.pages.IPlayersPage;
import practice3.pages.PlayersPage;

/**
 * Created by ly0w on 02.12.2016.
 * Класс SearchTest
 * Реализовать наиболее важные и приоритетные сценарии для проверки формы Search на странице Players.
 */
public class SearchTest {
    private WebDriver driver; // Declare var
    private IPlayersPage IPlayersPage;
    private CRUDUserTests crudUserTests;

    @BeforeTest
    public void beforeTest() {
        crudUserTests = new CRUDUserTests();
        crudUserTests.beforeTest();
        IPlayersPage = new PlayersPage(driver);
    }

    @AfterTest
    public void afterTest(){
        crudUserTests.afterTest();
    }
}
