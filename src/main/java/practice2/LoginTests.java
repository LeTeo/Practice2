package practice2;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by Serhii on 25-Nov-16.
 * Sellenium Lab
 */
public class LoginTests {

    private static final String URL_AUTH_LOGIN = "http://80.92.229.236:81/auth/login";
    private static final String USERNAME_AUTH_LOGIN = "username";
    private static final String ADMIN = "admin";
    private static final String PASSWORD = "password";
    private static final String ADMIN_PASSWORD = "123";
    private static final String ID_LOGIN = "logIn";
    private static final IPokerPlayer INSERT_FORM = new PokerPlayer(
            "ff14642ac1c__us_login", "ff14642ac1c__us_password","ff14642ac1c__confirm_password","ff14642ac1c__us_email","ff14642ac1c__us_fname",
            "ff14642ac1c__us_lname","ff14642ac1c__us_city","ff14642ac1c__us_address","ff14642ac1c__us_phone", "ff14642ac1c__us_country");
    private static final IPokerPlayer EDIT_FORM = new PokerPlayer(
            "ff14642ac1c__us_login","ff14642ac1c__us_email","ff14642ac1c__us_fname",
            "ff14642ac1c__us_lname","ff14642ac1c__us_city","ff14642ac1c__us_address","ff14642ac1c__us_phone", "ff14642ac1c__us_country");
    private static final String PLAYERS = "Players";
    private static final String XPATH_PLAYERS_INSERT = ".//a[@href='http://80.92.229.236:81/players/insert']";
    private static final String BUTTON_SAVE = "button_save";
    private static final String USERNAME_FIELD_ID = "723a925886__login";
    private static final String XPATH_INPUT_SEARCH = ".//input[@VALUE='Search']";
    private static final String XPATH_FIND_USERNAME_OPEN = ".//tr[.//a[text()='";
    private static final String XPATH_FIND_USERNAME_CLOSE = "']]//img[@title='Edit']";
    private static final String VALUE = "value";
    private static WebDriver driver = null;

    /**
     * Login on login page, type username "admin" and "123". Then click button login on login page
     */
    private static void loginOnLoginPage(){
        driver.get(URL_AUTH_LOGIN); // open Poker

        WebElement usernameInput = driver.findElement(By.id(USERNAME_AUTH_LOGIN));
        usernameInput.sendKeys(ADMIN);

        WebElement passwordInput = driver.findElement(By.id(PASSWORD));
        passwordInput.sendKeys(ADMIN_PASSWORD);

        System.out.println(usernameInput.getText());

        WebElement loginButton = driver.findElement(By.id(ID_LOGIN));
        loginButton.click();
    }

    /**
     * Main scenario
     * @param args not using
     */
    public static void main(String[] args) {
        driver = new FirefoxDriver(); // open browser
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //set timeout, when open any page
        //1 Login in system http://80.92.229.236:81/auth/login
        loginOnLoginPage();
        //2 Create user (Insert), with filled fields: username email First Name Last Name city address phone
        driver.findElement(By.xpath(XPATH_PLAYERS_INSERT)).click();// insert button click
        IPokerPlayer pokerPlayer = createRandomPokerPlayer();
        insertPokerPlayerIntoForm(pokerPlayer);
        driver.findElement(By.name(BUTTON_SAVE)).click();
        //3 Проверка, что пользователь вернулся на страницу со списком игроков
        assertString(driver.getTitle(), PLAYERS); //Check title
        //4 Поиск созданного игрока по username
        findPlayerOnPlayersByUsername(pokerPlayer.getUsername());
        //5 Открытие формы редактирование этого игрока
        openEditFormByUsername(pokerPlayer.getUsername());
        //6 Проверки содержания следующих полей: email First Name Last Name city address phone
        IPokerPlayer pokerPlayerFromEditForm = getPokerPlayerFromEditForm(); //actual pokerPlayer fields
        assertFields(pokerPlayerFromEditForm, pokerPlayer);
        //7 Изменить содержание полей, сохранить
        editPokerPlayerIntoForm(pokerPlayer, pokerPlayerFromEditForm.getCountry());
        driver.findElement(By.name(BUTTON_SAVE)).click();
        //8 Снова открыть форму редактирования, проверить, что изменения успешно применены
        openEditFormByUsername(pokerPlayer.getUsername());
        pokerPlayerFromEditForm = getPokerPlayerFromEditForm();
        assertFields(pokerPlayerFromEditForm, pokerPlayer);
        driver.quit(); //close browser
    }

    /**
     * Find players by username
     * @param username player username
     */
    private static void findPlayerOnPlayersByUsername(String username){
        WebElement usernameField = driver.findElement(By.id(USERNAME_FIELD_ID));
        usernameField.sendKeys(username);
        driver.findElement(By.xpath(XPATH_INPUT_SEARCH)).click();
    }

    /**
     * Create poker player with random fields
     * @return player with random fields
     */
    private static IPokerPlayer createRandomPokerPlayer(){
        return new PokerPlayer().RandomFields();
    }

    /**
     * Click on edit button form Players page when be search by username
     * @param username username who was find
     */
    private static void openEditFormByUsername(String username) {
        driver.findElement(By.xpath(XPATH_FIND_USERNAME_OPEN
                + username + XPATH_FIND_USERNAME_CLOSE)).click();
    }

    /**
     * Verify two player with six fields: email First Name Last Name city address phone
     * @param actualPokerPlayer actual player with fields: email First Name Last Name city address phone
     * @param expectedPokerPlayer expected player with fields: email First Name Last Name city address phone
     */
    private static void assertFields(IPokerPlayer actualPokerPlayer, IPokerPlayer expectedPokerPlayer){
        assertString(actualPokerPlayer.getEmail(), expectedPokerPlayer.getEmail());
        assertString(actualPokerPlayer.getFirstName(), expectedPokerPlayer.getFirstName());
        assertString(actualPokerPlayer.getLastName(), expectedPokerPlayer.getLastName());
        assertString(actualPokerPlayer.getCity(), expectedPokerPlayer.getCity());
        assertString(actualPokerPlayer.getAddress(), expectedPokerPlayer.getAddress());
        assertString(actualPokerPlayer.getPhone(), expectedPokerPlayer.getPhone());
    }

    /**
     * Verify two string
     * @param actualResult actual string
     * @param expectedResult expected string
     */
    private static void assertString(String actualResult, String expectedResult) {
        if(expectedResult.equals(actualResult)) {
            System.out.println("Passed.");
        } else {
            System.err.println("Failed. Expected: " + expectedResult
                    + ". Actual: " + actualResult);
        }
    }

    /**
     * Get player with eight fields from edit form:
     * @return player with eight fields fro
     */
    private static IPokerPlayer getPokerPlayerFromEditForm(){
        return new PokerPlayer(
                driver.findElement(By.id(EDIT_FORM.getUsername())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getEmail())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getFirstName())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getLastName())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getCity())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getAddress())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getPhone())).getAttribute(VALUE),
                driver.findElement(By.id(EDIT_FORM.getCountry())).getAttribute(VALUE)
        );
    }

    /**
     * Get player with ten fields from insert form:
     * @return player with ten fields from insert form
     */
    private static IPokerPlayer getPokerPlayerFromInsertedForm(){
        return new PokerPlayer(
                driver.findElement(By.id(INSERT_FORM.getUsername())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getPassword())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getConfirmPassword())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getEmail())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getFirstName())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getLastName())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getCity())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getAddress())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getPhone())).getAttribute(VALUE),
                driver.findElement(By.id(INSERT_FORM.getCountry())).getAttribute(VALUE)
        );
    }

    /**
     * Correct errors on EDIT_FORM
     * @param pokerPlayer created player
     * @param actualCountry right value country
     * @return player from edit form fields
     */
    private static IPokerPlayer editPokerPlayerIntoForm(IPokerPlayer pokerPlayer, String actualCountry){
        //swap LastName & FirstName
        WebElement lastNameElement = driver.findElement(By.id(INSERT_FORM.getLastName()));
        lastNameElement.clear();
        lastNameElement.sendKeys(pokerPlayer.getFirstName());
        WebElement firstNameElement = driver.findElement(By.id(INSERT_FORM.getFirstName()));
        firstNameElement.clear();
        firstNameElement.sendKeys(pokerPlayer.getLastName());
        //insert address player in address
        driver.findElement(By.id(INSERT_FORM.getAddress())).sendKeys(pokerPlayer.getAddress());
        //set country from parameter actualCountry
        WebElement countryElement = driver.findElement(By.id(INSERT_FORM.getCountry()));
        //countryElement.clear();
        countryElement.sendKeys(actualCountry);
        return getPokerPlayerFromEditForm();
    }

    /**
     * insert PokerPlayer fields into form
     * @param pokerPlayer player with ten fields: username, password, ...
     * @return poker player from fields
     */
    private static IPokerPlayer insertPokerPlayerIntoForm(IPokerPlayer pokerPlayer){
        driver.findElement(By.id(INSERT_FORM.getUsername())).sendKeys(pokerPlayer.getUsername());
        driver.findElement(By.id(INSERT_FORM.getPassword())).sendKeys(pokerPlayer.getPassword());
        driver.findElement(By.id(INSERT_FORM.getConfirmPassword())).sendKeys(pokerPlayer.getConfirmPassword());
        driver.findElement(By.id(INSERT_FORM.getEmail())).sendKeys(pokerPlayer.getEmail());
        driver.findElement(By.id(INSERT_FORM.getFirstName())).sendKeys(pokerPlayer.getFirstName());//5
        driver.findElement(By.id(INSERT_FORM.getLastName())).sendKeys(pokerPlayer.getLastName());
        driver.findElement(By.id(INSERT_FORM.getCity())).sendKeys(pokerPlayer.getCity());
        driver.findElement(By.id(INSERT_FORM.getAddress())).sendKeys(pokerPlayer.getAddress());
        driver.findElement(By.id(INSERT_FORM.getPhone())).sendKeys(pokerPlayer.getPhone());
        driver.findElement(By.id(INSERT_FORM.getCountry())).sendKeys(pokerPlayer.getCountry());//5

        return getPokerPlayerFromInsertedForm();
    }

}
