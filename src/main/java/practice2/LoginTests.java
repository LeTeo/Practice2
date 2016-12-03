package practice2;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by Serhii on 25-Nov-16.
 */
public class LoginTests {

    public static final String urlAuthLogin = "http://80.92.229.236:81/auth/login";
    public static final String usernameAuthLogin = "username";
    public static final String admin = "admin";
    public static final String password = "password";
    public static final String adminPassword = "123";
    public static final String logIn = "logIn";

    public static final PokerPlayer insertForm = new PokerPlayer(
            "ff14642ac1c__us_login", "ff14642ac1c__us_password","ff14642ac1c__confirm_password","ff14642ac1c__us_email","ff14642ac1c__us_fname",
            "ff14642ac1c__us_lname","ff14642ac1c__us_city","ff14642ac1c__us_address","ff14642ac1c__us_phone", "ff14642ac1c__us_country");

    public static final PokerPlayer editForm = new PokerPlayer(
            "ff14642ac1c__us_login","ff14642ac1c__us_email","ff14642ac1c__us_fname",
            "ff14642ac1c__us_lname","ff14642ac1c__us_city","ff14642ac1c__us_address","ff14642ac1c__us_phone", "ff14642ac1c__us_country");

    public static final String Players = "Players";
    public static final String xpathPlayersInsert = ".//a[@href='http://80.92.229.236:81/players/insert']";
    public static final String button_save = "BUTTON_SAVE";
    public static final String usernameFieldId = "723a925886__login";
    public static final String xpathInputSearch = ".//input[@VALUE='Search']";
    public static final String xpathFindUsernameOpen = ".//tr[.//a[text()='";
    public static final String xpathFindUsernameClose = "']]//img[@title='Edit']";
    public static final String value = "VALUE";

    private static WebDriver driver = null;

    public static void LoginOnLoginPage(){
        driver.get(urlAuthLogin); // open Poker

        WebElement usernameInput = driver.findElement(By.id(usernameAuthLogin));
        usernameInput.sendKeys(admin);

        WebElement passwordInput = driver.findElement(By.id(password));
        passwordInput.sendKeys(adminPassword);

        System.out.println(usernameInput.getText());

        WebElement loginButton = driver.findElement(By.id(logIn));
        loginButton.click();
    }

    public static void main(String[] args) {

        driver = new FirefoxDriver(); // open browser
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //set timeout, when open any page
        //1 Login in system http://80.92.229.236:81/auth/login
        LoginOnLoginPage();
        //2 Create user (Insert), with filled fields: username email First Name Last Name city address phone
        driver.findElement(By.xpath(xpathPlayersInsert)).click();// insert button click
        PokerPlayer pokerPlayer = createRandomPokerPlayer();
        insertPokerPlayerIntoForm(pokerPlayer);
        driver.findElement(By.name(button_save)).click();
        //3 Проверка, что пользователь вернулся на страницу со списком игроков
        assertString(driver.getTitle(), Players); //Check title
        //4 Поиск созданного игрока по username
        findPlayerOnPlayersByUsername(pokerPlayer.getUsername());
        //5 Открытие формы редактирование этого игрока
        openEditFormByUsername(pokerPlayer.getUsername());
        //6 Проверки содержания следующих полей: email First Name Last Name city address phone
        PokerPlayer pokerPlayerFromEditForm = getPokerPlayerFromEditForm(); //actual pokerPlayer fields
        assertFields(pokerPlayerFromEditForm, pokerPlayer);
        //7 Изменить содержание полей, сохранить
        editPokerPlayerIntoForm(pokerPlayer, pokerPlayerFromEditForm.getCountry());
        driver.findElement(By.name(button_save)).click();
        //8 Снова открыть форму редактирования, проверить, что изменения успешно применены
        openEditFormByUsername(pokerPlayer.getUsername());
        pokerPlayerFromEditForm = getPokerPlayerFromEditForm();
        assertFields(pokerPlayerFromEditForm, pokerPlayer);

        driver.quit(); //close browser
    }

    public static void findPlayerOnPlayersByUsername(String username){
        WebElement usernameField = driver.findElement(By.id(usernameFieldId));
        usernameField.sendKeys(username);
        driver.findElement(By.xpath(xpathInputSearch)).click();
    }

    public static PokerPlayer createRandomPokerPlayer(){
        return new PokerPlayer().RandomFields();
    }

    public static void openEditFormByUsername(String username) {
        driver.findElement(By.xpath(xpathFindUsernameOpen
                + username + xpathFindUsernameClose)).click();
    }

    public static void assertFields(PokerPlayer actualPokerPlayer, PokerPlayer expectedPokerPlayer){
        assertString(actualPokerPlayer.getEmail(), expectedPokerPlayer.getEmail());
        assertString(actualPokerPlayer.getFirst_Name(), expectedPokerPlayer.getFirst_Name());
        assertString(actualPokerPlayer.getLast_Name(), expectedPokerPlayer.getLast_Name());
        assertString(actualPokerPlayer.getCity(), expectedPokerPlayer.getCity());
        assertString(actualPokerPlayer.getAddress(), expectedPokerPlayer.getAddress());
        assertString(actualPokerPlayer.getPhone(), expectedPokerPlayer.getPhone());
    }

    public static void assertString(String actualResult, String expectedResult) {
        if(expectedResult.equals(actualResult)) {
            System.out.println("Passed.");
        } else {
            System.err.println("Failed. Expected: " + expectedResult
                    + ". Actual: " + actualResult);
        }
    }

    public static PokerPlayer getPokerPlayerFromEditForm(){
        //Hashtable <String, String>
        return new PokerPlayer(

                driver.findElement(By.id(editForm.getUsername())).getAttribute(value),
                driver.findElement(By.id(editForm.getEmail())).getAttribute(value),
                driver.findElement(By.id(editForm.getFirst_Name())).getAttribute(value),
                driver.findElement(By.id(editForm.getLast_Name())).getAttribute(value),
                driver.findElement(By.id(editForm.getCity())).getAttribute(value),
                driver.findElement(By.id(editForm.getAddress())).getAttribute(value),
                driver.findElement(By.id(editForm.getPhone())).getAttribute(value),
                driver.findElement(By.id(editForm.getCountry())).getAttribute(value)

        );
    }

    public static PokerPlayer getPokerPlayerFromInsertedForm(){

        return new PokerPlayer(
                driver.findElement(By.id(insertForm.getUsername())).getAttribute(value),
                driver.findElement(By.id(insertForm.getPassword())).getAttribute(value),
                driver.findElement(By.id(insertForm.getConfirmPassword())).getAttribute(value),
                driver.findElement(By.id(insertForm.getEmail())).getAttribute(value),
                driver.findElement(By.id(insertForm.getFirst_Name())).getAttribute(value),
                driver.findElement(By.id(insertForm.getLast_Name())).getAttribute(value),
                driver.findElement(By.id(insertForm.getCity())).getAttribute(value),
                driver.findElement(By.id(insertForm.getAddress())).getAttribute(value),
                driver.findElement(By.id(insertForm.getPhone())).getAttribute(value),
                driver.findElement(By.id(insertForm.getCountry())).getAttribute(value)
        );
    }

    //Correct errors on EDIT_FORM
    public static PokerPlayer editPokerPlayerIntoForm(PokerPlayer pokerPlayer, String actualCountry){

        //swap LastName & FirstName
        WebElement lastNameElement = driver.findElement(By.id(insertForm.getLast_Name()));
        lastNameElement.clear();
        lastNameElement.sendKeys(pokerPlayer.getFirst_Name());
        WebElement firstNameElement = driver.findElement(By.id(insertForm.getFirst_Name()));
        firstNameElement.clear();
        firstNameElement.sendKeys(pokerPlayer.getLast_Name());


        driver.findElement(By.id(insertForm.getAddress())).sendKeys(pokerPlayer.getAddress());


        //set country from parameter @actualCountry
        WebElement countryElement = driver.findElement(By.id(insertForm.getCountry()));
//        countryElement.clear();
        countryElement.sendKeys(actualCountry);

        return getPokerPlayerFromEditForm();
    }

    //insert PokerPlayer fields into form
    public static PokerPlayer insertPokerPlayerIntoForm(PokerPlayer pokerPlayer){
        driver.findElement(By.id(insertForm.getUsername())).sendKeys(pokerPlayer.getUsername());
        driver.findElement(By.id(insertForm.getPassword())).sendKeys(pokerPlayer.getPassword());
        driver.findElement(By.id(insertForm.getConfirmPassword())).sendKeys(pokerPlayer.getConfirmPassword());
        driver.findElement(By.id(insertForm.getEmail())).sendKeys(pokerPlayer.getEmail());
        driver.findElement(By.id(insertForm.getFirst_Name())).sendKeys(pokerPlayer.getFirst_Name());//5
        driver.findElement(By.id(insertForm.getLast_Name())).sendKeys(pokerPlayer.getLast_Name());
        driver.findElement(By.id(insertForm.getCity())).sendKeys(pokerPlayer.getCity());
        driver.findElement(By.id(insertForm.getAddress())).sendKeys(pokerPlayer.getAddress());
        driver.findElement(By.id(insertForm.getPhone())).sendKeys(pokerPlayer.getPhone());
        driver.findElement(By.id(insertForm.getCountry())).sendKeys(pokerPlayer.getCountry());//5

        return getPokerPlayerFromInsertedForm();
    }

}
