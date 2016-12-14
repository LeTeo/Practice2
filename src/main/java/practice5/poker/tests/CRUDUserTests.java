package practice5.poker.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import practice5.draggable.tests.DragAndDrop2Tests;
import practice5.poker.classes.CRUDPokerPlayer;
import practice5.poker.interfaces.IPokerPlayer;
import practice5.poker.interfaces.pages.IEditPlayerPage;
import practice5.poker.interfaces.pages.IInsertPlayerPage;
import practice5.poker.pages.EditPlayerPage;
import practice5.poker.pages.InsertPlayerPage;
import practice5.poker.pages.LoginPage;
import practice5.poker.pages.PlayersPage;

import java.util.concurrent.TimeUnit;


/**
 * Created by ly0w on 02.12.2016.
 * Класс CRUDUserTests.java(Create Read/Update Delete операции)
 * Реализовать тест на создание, изменение и удаление игрока.
 * Количество и детализация тестовых сценариев зависит от вас. Делайте как считаете нужным.
 */
public class CRUDUserTests  {

    //private static final String INSERT_GROUP = "Insert";//Declare and initialize const
    //private static final String COPY_UPDATE_GROUP = "Copy-Update";//Declare and initialize const
    //private static final String DELETE_GROUP = "Delete";
    private static final String YOU_ARE_NOT_ON_INSERT_PAGE = "You are NOT on insert page";
    private static final String YOU_ARE_NOT_ON_EDIT_PAGE = "You are NOT on edit page";
    private static final String YOU_ARE_NOT_ON_PLAYERS_PAGE = "You are NOT on players page";
    //private static final String WRONG_TITLE = "Wrong title";
    private static final String WRONG_TITLE_INSERT = "Wrong title insert page";
    private static final String WRONG_TITLE_EDIT = "Wrong title edit page";
    private static final String WRONG_TITLE_PLAYERS = "Wrong title players page";
    private static final String WRONG_FIRST_NAME = "Wrong First Name";
    private static final String WRONG_EMAIL = "Wrong email";
    private static final String WRONG_LAST_NAME = "Wrong Last Name";
    private static final String WRONG_CITY = "Wrong city";
    private static final String WRONG_ADDRESS = "Wrong address";
    private static final String WRONG_PHONE = "Wrong phone";
    private static final String DELETE_ALERT_TEXT = "Do you really want to delete the record?";
    private static final String WRONG_MODAL_DIALOG = "Wrong modal dialog";
    private static final String WRONG_DELETE_MESSAGE = "Wrong delete message";
    //private static final String ERROR_READING_FIELDS = "ERROR READING FIELDS";
    //private static final String POKER_PLAYER_NOT_CREATED = "Poker player not created";
    private static final String EMPTY_STRING_ADDRESS = "";
    private static final String USER_DOES_NOT_EXIST = "User does not exist";
    public static WebDriver driver; // Declare var
    private PlayersPage playersPage;
    private IInsertPlayerPage insertPlayerPage;
    private IEditPlayerPage editPlayerPage;
    private SoftAssert softAssert;
    //private IPokerPlayer pokerPlayer;

    /**
     * Precondition:
     * 1. Open browser
     * 2-3. Sets timeout
     * 4-6. Positive login test
     * 7-9. Initialize pages classes
     */
    @BeforeSuite
    public void beforeSuite() {
        if(LoginTests.driver == null)
            driver = new FirefoxDriver();
        else if(DragAndDrop2Tests.driver == null)
            driver = LoginTests.driver;
        else
            driver = DragAndDrop2Tests.driver;
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.authorization(LoginTests.TEST_POSITIVE_VALUE.getUsername(), LoginTests.TEST_POSITIVE_VALUE.getPassword());
        playersPage = new PlayersPage(driver);
        insertPlayerPage = new InsertPlayerPage(driver);
        editPlayerPage = new EditPlayerPage(driver);
    }

    /**
     * Precondition
     * Click on button reset for reset search
     */
    @BeforeMethod
    public void beforeMethod(){
        playersPage.open();
        playersPage.clickOnResetButton();
        softAssert = new SoftAssert();
    }

    @DataProvider
    private Object[][] insertAndUpdatePositivePokerPlayersData(){
        return new Object[][]{
            {CRUDPokerPlayer.randomPokerPlayer()},
            {CRUDPokerPlayer.randomPokerPlayer()},
        };
    }

    /**
     * Steps to reproduce:
     * 1. Click on insert button
     * 2-3. Check actual title, url and title, url on insert player page
     * 4. Create player pokerPlayer, with random fields: username email First Name Last Name city address phone
     * 5-6. Check actual title, url and title, url on players page
     * 7. Search created player by username Поиск созданного игрока по username
     * 8. Open edit form for this player Открытие формы редактирование этого игрока
     * 9. Check actual title and expected title on edit page
     * 10. Get actual pokerPlayer from edit page. Save them into pokerPlayerFromEditForm.
     * 11. Check fields pokerPlayerFromEditForm: email First Name Last Name city address phone  Проверки содержания следующих полей: email First Name Last Name city address phone
     * 12. Edit fields values and click button save Изменить содержание полей, сохранить
     * 13-15. Again open edit form for edit and check fields after previously edit Снова открыть форму редактирования, проверить, что изменения успешно применены
     */
    @Test(dataProvider = "insertAndUpdatePositivePokerPlayersData")//(groups = INSERT_GROUP)
    public void insertUserPositiveTest(IPokerPlayer insertPokerPlayer){

        playersPage.clickOnInsertButton();IPokerPlayer pokerPlayer;
        softAssertUrlAndTitleEquals(IInsertPlayerPage.TITLE, IInsertPlayerPage.URL,
                WRONG_TITLE_INSERT, YOU_ARE_NOT_ON_INSERT_PAGE);softAssert.assertAll();
        pokerPlayer = insertPlayerPage.insertPokerPlayer(insertPokerPlayer);
        softAssertUrlAndTitleEquals(PlayersPage.TITLE, PlayersPage.URL,
                WRONG_TITLE_PLAYERS, YOU_ARE_NOT_ON_PLAYERS_PAGE);softAssert.assertAll();
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        playersPage.openEditFormByUsername(pokerPlayer.getUsername());
        Assert.assertEquals(driver.getTitle(), IEditPlayerPage.TITLE, WRONG_TITLE_EDIT);
        IPokerPlayer pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        assertFieldsWithErrors(pokerPlayerFromEditForm, pokerPlayer);
        editPlayerPage.editPokerPlayerAndSaveAfterInsert(pokerPlayer);
        playersPage.openEditFormByUsername(pokerPlayer.getUsername());
        pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        assertFields(pokerPlayerFromEditForm, pokerPlayer);
    }

    /**
     * Steps to reproduce:
     * 1. Open players page
     * 2. Click on insert button on players page
     * 3. Verify Player - Insert title
     * 4. Verify "http://80.92.229.236:81/players/insert"; url
     * 5. Click on cancel button
     * 6. Switch to alert after click
     * 7. Verify alert text
     * 8. Click on alert accept button
     * 9. Verify title Player - Insert page
     */
    @Test
    public void insertUserNegativeAlertCancelAcceptTest(){
        playersPage.open();
        playersPage.clickOnInsertButton();
        Assert.assertEquals(driver.getTitle(), IInsertPlayerPage.TITLE, WRONG_TITLE_INSERT);
        Assert.assertEquals(driver.getCurrentUrl(), IInsertPlayerPage.URL, YOU_ARE_NOT_ON_INSERT_PAGE);
        insertPlayerPage.clickOnButtonCancel();
        Alert alertCancel = driver.switchTo().alert();
        Assert.assertEquals(alertCancel.getText(), IInsertPlayerPage.CANCEL_ALERT_MESSAGE,WRONG_MODAL_DIALOG);
        alertCancel.accept();
        Assert.assertEquals(driver.getTitle(), IInsertPlayerPage.TITLE,WRONG_TITLE_INSERT);
    }

    /**
     * Steps to reproduce:
     * 1. Open players page
     * 2. Click on insert button on players page
     * 3. Verify Player - Insert title
     * 4. Verify "http://80.92.229.236:81/players/insert"; url
     * 5. Click on cancel button
     * 6. Switch to alert after click
     * 7. Verify alert text
     * 8. Click on alert cancel button
     * 9. Verify title Players page
     */
    @Test
    public void insertUserNegativeAlertCancelDismissTest(){
        playersPage.open();
        playersPage.clickOnInsertButton();
        Assert.assertEquals(driver.getTitle(), IInsertPlayerPage.TITLE, WRONG_TITLE_INSERT);
        Assert.assertEquals(driver.getCurrentUrl(), IInsertPlayerPage.URL, YOU_ARE_NOT_ON_INSERT_PAGE);
        insertPlayerPage.insertRandomPokerPlayerAndClickButtonCancel();
        Alert alertCancel = driver.switchTo().alert();
        Assert.assertEquals(alertCancel.getText(), IInsertPlayerPage.CANCEL_ALERT_MESSAGE,WRONG_MODAL_DIALOG);
        alertCancel.dismiss();
        Assert.assertEquals(driver.getTitle(), PlayersPage.TITLE, WRONG_TITLE_PLAYERS);
    }

    /**
     * Steps to reproduce:
     * 1. Open players page and get last created player
     * 2. Find player by username
     * 3. Click on edit button
     * 4. Check actual title and title edit player page
     * 5. Check actual url and url edit player page
     * 6. Get poker player from edit form fields
     * 7. Generate random fields for updated player and include in this player field username from get poker player from edit form fields
     * 8. Insert random with correct fields and click on button save
     * 9-10. Click on edit button and search updated user.
     * 11. Verify fields updated player with errors
     * 12-15. Again open edit form for edit and check fields after previously edit Снова открыть форму редактирования, проверить, что изменения успешно применены
     * @param updatePokerPlayer poker player with random fields, username, password, confirmPassword
     */
    @Test(dataProvider = "insertAndUpdatePositivePokerPlayersData")//(dependsOnGroups = INSERT_GROUP, groups = COPY_UPDATE_GROUP)
    public void updateLastByIdUserTest(IPokerPlayer updatePokerPlayer){
        IPokerPlayer pokerPlayerUpdated = playersPage.findUserPokerPlayerNameAndEmailLastCreatedUserById();
        playersPage.findPlayerByUsername(pokerPlayerUpdated.getUsername());
        playersPage.clickOnEditButton(pokerPlayerUpdated.getUsername());
        Assert.assertEquals(driver.getTitle(), IEditPlayerPage.TITLE, WRONG_TITLE_EDIT);
        Assert.assertEquals(driver.getCurrentUrl().substring(0, IEditPlayerPage.URL.length()), IEditPlayerPage.URL, YOU_ARE_NOT_ON_EDIT_PAGE);
        IPokerPlayer pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        IPokerPlayer updatedPokerPlayerRandom = CRUDPokerPlayer.editPokerPlayer(pokerPlayerFromEditForm, updatePokerPlayer);
        editPlayerPage.editPokerPlayerAndSaveWithoutCorrectErrors(updatedPokerPlayerRandom);
        playersPage.clickOnEditButton(updatedPokerPlayerRandom.getUsername());
        pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        assertFieldsWithErrorsForEdit(pokerPlayerFromEditForm, updatedPokerPlayerRandom);
        editPlayerPage.editPokerPlayerAndSave(updatedPokerPlayerRandom,pokerPlayerFromEditForm);
        playersPage.openEditFormByUsername(updatedPokerPlayerRandom.getUsername());
        pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        assertFields(pokerPlayerFromEditForm, updatedPokerPlayerRandom);
    }

    /**
     * Steps to reproduce:
     * 1-2. Open players page Find last created poker player by high id
     * 3. Click on delete button with find name poker player
     * 4. Switch to delete modal dialog (alert)
     * 5. Verify Title delete modal dialog
     * 6. Click accept on delete modal dialog
     * 7. Verify delete message on players page
     * 8. Verify title on players page
     */
    @Test
    public void PositiveDeleteLastByIdUserTest(){
        IPokerPlayer deletedPokerPlayer = playersPage.findUserPokerPlayerNameAndEmailLastCreatedUserById();
        playersPage.findPlayerByUsernameAndEmail(deletedPokerPlayer.getUsername(),deletedPokerPlayer.getEmail());
        playersPage.clickOnDeleteButton(deletedPokerPlayer.getUsername());
        Alert deleteAlert = driver.switchTo().alert();
        Assert.assertEquals(deleteAlert.getText(), DELETE_ALERT_TEXT, WRONG_MODAL_DIALOG);
        deleteAlert.accept();
        Assert.assertEquals(playersPage.getDeleteMessage(), PlayersPage.DELETE_MESSAGE, WRONG_DELETE_MESSAGE);
        Assert.assertEquals(driver.getTitle(), PlayersPage.TITLE,YOU_ARE_NOT_ON_PLAYERS_PAGE);
    }

    /**
     * Steps to reproduce:
     * 2-3. Open players page, get poker player with high id and Find poker player by username
     * 4. Click on edit button with poker player with username
     * 5. Copy from edit player page fields in poker player
     * 6. Open players page
     * 7. Find poker player who was copy
     * 8. Generate new password and login name
     * 9. Insert new player
     */
    @Test//(groups = COPY_UPDATE_GROUP)
    public void copyLastByIdUserTest(){
        IPokerPlayer pokerPlayerClone = playersPage.findUserPokerPlayerNameAndEmailLastCreatedUserById();
        playersPage.findPlayerByUsername(pokerPlayerClone.getUsername());
        playersPage.clickOnEditButton(pokerPlayerClone.getUsername());
        pokerPlayerClone = editPlayerPage.getPokerPlayerFromEditForm();
        playersPage.open();
        playersPage.findPlayerByUsername(pokerPlayerClone.getUsername());
        playersPage.insertPlayerClone(playersPage, pokerPlayerClone, editPlayerPage);
    }

    /**
     * Steps to reproduce:
     * 3. Find player last created by id
     * 4. Click on edit button (icon in right of the middle border on players page)
     * 5. Copy fields into poker player from program
     * 6. Verify if exist stored player in program
     */
    @Test//(groups = INSERT_GROUP)
    public void readLastByIdUserTest(){
        IPokerPlayer readPokerPlayer = playersPage.findUserPokerPlayerNameAndEmailLastCreatedUserById();
        playersPage.findPlayerByUsernameAndEmail(readPokerPlayer);
        playersPage.clickOnEditButton(readPokerPlayer.getUsername());
        readPokerPlayer = editPlayerPage.getPokerPlayerFromEditForm();
        Assert.assertEquals(driver.getTitle(), IEditPlayerPage.TITLE, YOU_ARE_NOT_ON_EDIT_PAGE);
        Assert.assertEquals(readPokerPlayer.getUsername() == null, false, USER_DOES_NOT_EXIST);
    }

    /**
     * close browser
     */
    @AfterSuite()
    public void afterSuite(){
        driver.quit();
    }

    private void softAssertUrlAndTitleEquals(String title, String url, String wrongTitle, String youAreNotOnPage) {
//        softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(), title, wrongTitle);
        softAssert.assertEquals(driver.getCurrentUrl(), url, youAreNotOnPage);
//        softAssert.assertAll();
    }

    /**
     * Verify players fields with mistakes for both variant field getAddress
     * @param actualPokerPlayer actual player with wrong fields
     * @param expectedPokerPlayer expected player with fields
     */
    private void assertFieldsWithErrorsForBoth(IPokerPlayer actualPokerPlayer, IPokerPlayer expectedPokerPlayer) {
        Assert.assertEquals(actualPokerPlayer.getEmail(), expectedPokerPlayer.getEmail(), WRONG_EMAIL);
        Assert.assertEquals(actualPokerPlayer.getFirstName(), expectedPokerPlayer.getLastName(), WRONG_FIRST_NAME);
        Assert.assertEquals(actualPokerPlayer.getLastName(), expectedPokerPlayer.getFirstName(), WRONG_LAST_NAME);
        Assert.assertEquals(actualPokerPlayer.getCity(), expectedPokerPlayer.getCity(), WRONG_CITY);
        Assert.assertEquals(actualPokerPlayer.getPhone(), expectedPokerPlayer.getPhone(), WRONG_PHONE);
    }

    /**
     * Verify players fields with mistakes
     * @param actualPokerPlayer actual player with wrong fields
     * @param expectedPokerPlayer expected player with fields
     */
    private void assertFieldsWithErrors(IPokerPlayer actualPokerPlayer, IPokerPlayer expectedPokerPlayer) {
        assertFieldsWithErrorsForBoth(actualPokerPlayer, expectedPokerPlayer);
        Assert.assertEquals(actualPokerPlayer.getAddress(), EMPTY_STRING_ADDRESS, WRONG_ADDRESS);
    }

    /**
     * Verify players fields with mistakes for edit
     * @param actualPokerPlayer actual player with wrong fields
     * @param expectedPokerPlayer expected player with fields
     */
    private void assertFieldsWithErrorsForEdit(IPokerPlayer actualPokerPlayer, IPokerPlayer expectedPokerPlayer) {
        assertFieldsWithErrorsForBoth(actualPokerPlayer,expectedPokerPlayer);
        Assert.assertEquals(actualPokerPlayer.getAddress(), expectedPokerPlayer.getAddress(), WRONG_ADDRESS);
    }

    /**
     * Strong verify players correct filled fields
     * @param actualPokerPlayer actual player with correct filled fields
     * @param expectedPokerPlayer expected player with correct filled fields
     */
    private void assertFields(IPokerPlayer actualPokerPlayer, IPokerPlayer expectedPokerPlayer) {
        Assert.assertEquals(actualPokerPlayer.getEmail(), expectedPokerPlayer.getEmail(), WRONG_EMAIL);
        Assert.assertEquals(actualPokerPlayer.getFirstName(), expectedPokerPlayer.getFirstName(), WRONG_FIRST_NAME);
        Assert.assertEquals(actualPokerPlayer.getLastName(), expectedPokerPlayer.getLastName(), WRONG_LAST_NAME);
        Assert.assertEquals(actualPokerPlayer.getCity(), expectedPokerPlayer.getCity(), WRONG_CITY);
        Assert.assertEquals(actualPokerPlayer.getAddress(), expectedPokerPlayer.getAddress(), WRONG_ADDRESS);
        Assert.assertEquals(actualPokerPlayer.getPhone(), expectedPokerPlayer.getPhone(), WRONG_PHONE);
    }
}
