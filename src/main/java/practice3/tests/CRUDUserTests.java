package practice3.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import practice3.classes.CRUDPokerPlayer;
import practice3.classes.PokerPlayer;
import practice3.interfaces.pages.IEditPlayerPage;
import practice3.interfaces.pages.IInsertPlayerPage;
import practice3.pages.EditPlayerPage;
import practice3.pages.InsertPlayerPage;
import practice3.pages.PlayersPage;
import practice3.interfaces.IPokerPlayer;


/**
 * Created by ly0w on 02.12.2016.
 * Класс CRUDUserTests.java(Create Read/Update Delete операции)
 * Реализовать тест на создание, изменение и удаление игрока.
 * Количество и детализация тестовых сценариев зависит от вас. Делайте как считаете нужным.
 */
public class CRUDUserTests  {

    private static final String INSERT_GROUP = "Insert";//Declare and initialize const
    private static final String DELETE_GROUP = "Delete";
    private static final String PATH_TO_FILE_POKERPLAYERS = "pokerPlayers.txt";//name of file save poker players what have been created
    private static final String YOU_ARE_NOT_ON_INSERT_PAGE = "You are NOT on insert page";
    private static final String YOU_ARE_NOT_ON_EDIT_PAGE = "You are NOT on edit page";
    private static final String YOU_ARE_NOT_ON_PLAYERS_PAGE = "You are NOT on players page";
    private static final String WRONG_TITLE = "Wrong title";
    private static final String WRONG_FIRST_NAME = "Wrong First Name";
    private static final String WRONG_EMAIL = "Wrong email";
    private static final String WRONG_LAST_NAME = "Wrong Last Name";
    private static final String WRONG_CITY = "Wrong city";
    private static final String WRONG_ADDRESS = "Wrong address";
    private static final String WRONG_PHONE = "Wrong phone";
    private static final String DELETE_ALERT_TEXT = "Do you really want to delete the record?";
    private static final String WRONG_MODAL_DIALOG = "Wrong modal dialog";
    private static final String WRONG_DELETE_MESSAGE = "Wrong delete message";
    private static final String ERROR_READING_FIELDS = "ERROR READING FIELDS";
    private static final String POKER_PLAYER_NOT_CREATED = "Poker player not created";
    private static final String EMPTY_STRING_ADDRESS = "";
    private WebDriver driver; // Declare var
    private PlayersPage playersPage;
    private LoginTests loginTests;
    private IInsertPlayerPage insertPlayerPage;
    private IEditPlayerPage editPlayerPage;
    private IPokerPlayer pokerPlayer;

    private void setLoginTestsDriver() {
        setDriver(loginTests.getDriver());
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @BeforeTest
    public void beforeTest() {
        loginTests = new LoginTests();
        loginTests.beforeTest();
        loginTests.beforeMethod();
        loginTests.positiveTest();
        setLoginTestsDriver();
        playersPage = new PlayersPage(driver);
        insertPlayerPage = new InsertPlayerPage(driver);
        editPlayerPage = new EditPlayerPage(driver);
    }

    /**
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
    @Test(groups = INSERT_GROUP)
    public void insertUserPositive(){
        playersPage.clickOnInsertButton();
        Assert.assertEquals(driver.getTitle(), IInsertPlayerPage.TITLE, WRONG_TITLE);
        Assert.assertEquals(driver.getCurrentUrl(), IInsertPlayerPage.URL, YOU_ARE_NOT_ON_INSERT_PAGE);
        pokerPlayer = insertPlayerPage.insertRandomPokerPlayer();
        Assert.assertEquals(driver.getTitle(), PlayersPage.TITLE, WRONG_TITLE);
        Assert.assertEquals(driver.getCurrentUrl(), PlayersPage.URL, YOU_ARE_NOT_ON_INSERT_PAGE);
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        playersPage.openEditFormByUsername(pokerPlayer.getUsername());
        Assert.assertEquals(driver.getTitle(), IEditPlayerPage.TITLE, WRONG_TITLE);
        IPokerPlayer pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        assertFieldsWithErrors(pokerPlayerFromEditForm, pokerPlayer);
        editPlayerPage.editPokerPlayerAndSave(pokerPlayer,pokerPlayerFromEditForm);
        playersPage.openEditFormByUsername(pokerPlayer.getUsername());
        pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        assertFields(pokerPlayerFromEditForm, pokerPlayer);
    }

    /**
     * 1. Open players oage
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
    public void insertUserNegativeAlertCancelAccept(){
        playersPage.open();
        playersPage.clickOnInsertButton();
        Assert.assertEquals(driver.getTitle(), IInsertPlayerPage.TITLE, WRONG_TITLE);
        Assert.assertEquals(driver.getCurrentUrl(), IInsertPlayerPage.URL, YOU_ARE_NOT_ON_INSERT_PAGE);
        insertPlayerPage.clickOnButtonCancel();
        Alert alertCancel = driver.switchTo().alert();
        Assert.assertEquals(alertCancel.getText(), IInsertPlayerPage.CANCEL_ALERT_MESSAGE,WRONG_MODAL_DIALOG);
        alertCancel.accept();
        Assert.assertEquals(driver.getTitle(), IInsertPlayerPage.TITLE,WRONG_TITLE);
    }

    /**
     * 1. Open players oage
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
    public void insertUserNegativeAlertCancelDismiss(){
        playersPage.open();
        playersPage.clickOnInsertButton();
        Assert.assertEquals(driver.getTitle(), IInsertPlayerPage.TITLE, WRONG_TITLE);
        Assert.assertEquals(driver.getCurrentUrl(), IInsertPlayerPage.URL, YOU_ARE_NOT_ON_INSERT_PAGE);
        pokerPlayer = insertPlayerPage.insertRandomPokerPlayerAndClickButtonCancel();
        Alert alertCancel = driver.switchTo().alert();
        Assert.assertEquals(alertCancel.getText(), IInsertPlayerPage.CANCEL_ALERT_MESSAGE,WRONG_MODAL_DIALOG);
        alertCancel.dismiss();
        Assert.assertEquals(driver.getTitle(), PlayersPage.TITLE, WRONG_TITLE);
    }

    /**
     * Verify players fields with mistakes
     * @param actualPokerPlayer actual player with wrong fields
     * @param expectedPokerPlayer expected player with fields
     */
    private void assertFieldsWithErrors(IPokerPlayer actualPokerPlayer, IPokerPlayer expectedPokerPlayer) {
        Assert.assertEquals(actualPokerPlayer.getEmail(), expectedPokerPlayer.getEmail(), WRONG_EMAIL);
        Assert.assertEquals(actualPokerPlayer.getFirstName(), expectedPokerPlayer.getLastName(), WRONG_FIRST_NAME);
        Assert.assertEquals(actualPokerPlayer.getLastName(), expectedPokerPlayer.getFirstName(), WRONG_LAST_NAME);
        Assert.assertEquals(actualPokerPlayer.getCity(), expectedPokerPlayer.getCity(), WRONG_CITY);
        Assert.assertEquals(actualPokerPlayer.getAddress(), EMPTY_STRING_ADDRESS, WRONG_ADDRESS);
        Assert.assertEquals(actualPokerPlayer.getPhone(), expectedPokerPlayer.getPhone(), WRONG_PHONE);
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

    /**
     * 1. Open players page
     * 2. Find player by username
     * 3. Click on edit button
     * 4. Check actual title and title edit player page
     * 5. Check actual url and url edit player page
     * 6. Get poker player from edit form fields
     * 7. Generate random fields for updated player and include in this player field username from get poker player from edit form fields
     * 8. Click on button save
     * 9-10. Click on edit button and search updated user.
     * 11. Verify fields updated player with errors
     * 12-16. Again open edit form for edit and check fields after previously edit Снова открыть форму редактирования, проверить, что изменения успешно применены
     */
    @Test(dependsOnGroups = INSERT_GROUP)
    public void updateUser(){
        playersPage.open();
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        playersPage.clickOnEditButton(pokerPlayer.getUsername());
        Assert.assertEquals(driver.getTitle(), IEditPlayerPage.TITLE, WRONG_TITLE);
        Assert.assertEquals(driver.getCurrentUrl().substring(0, IEditPlayerPage.URL.length()), IEditPlayerPage.URL, YOU_ARE_NOT_ON_EDIT_PAGE);
        IPokerPlayer pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        IPokerPlayer updatedPokerPlayer = CRUDPokerPlayer.editPokerPlayer(pokerPlayer.RandomFields(),pokerPlayerFromEditForm);
        editPlayerPage.clickOnButtonSave();
        playersPage.clickOnEditButton(updatedPokerPlayer.getUsername());
        pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        assertFieldsWithErrors(pokerPlayerFromEditForm, updatedPokerPlayer);
        editPlayerPage.editPokerPlayerAndSave(pokerPlayer,pokerPlayerFromEditForm);
        playersPage.openEditFormByUsername(pokerPlayer.getUsername());
        pokerPlayerFromEditForm = editPlayerPage.getPokerPlayerFromEditForm();
        assertFields(pokerPlayerFromEditForm, pokerPlayer);
    }

    /**
     * 1. Open players page
     * 2-3. Find poker player TODO find poker player, another way
     * 4. Click on delete button with find name poker player
     * 5. Switch to delete modal dialog (alert)
     * 6. Verify Title delete modal dialog
     * 7. Click accept on delete modal dialog
     * 8. Verify delete message on players page
     * 9. Verify title on players page
     */
    @Test(dependsOnGroups = INSERT_GROUP, groups = DELETE_GROUP)
    //@Test
    public void PositiveDeleteUser(){
        playersPage.open();
        IPokerPlayer pokerPlayer = new PokerPlayer();
        //pokerPlayer.setUsername("user1500"); pokerPlayer.getUsernameLastCreatedPlayer();
        //pokerPlayer.setEmail("player1500@test.com"); pokerPlayer.getEmailLastCreatedPlayer();
        playersPage.findPlayerByUsernameAndEmail(pokerPlayer.getUsername(),pokerPlayer.getEmail());
        playersPage.clickOnDeleteButton(pokerPlayer.getUsername());
        Alert deleteAlert = driver.switchTo().alert();
        Assert.assertEquals(deleteAlert.getText(), DELETE_ALERT_TEXT, WRONG_MODAL_DIALOG);
        deleteAlert.accept();
        Assert.assertEquals(playersPage.getDeleteMessage(), PlayersPage.DELETE_MESSAGE, WRONG_DELETE_MESSAGE);
        Assert.assertEquals(driver.getTitle(),PlayersPage.TITLE,YOU_ARE_NOT_ON_PLAYERS_PAGE);
        //playersPage.findPlayerByUsername(pokerPlayer.getUsername());
    }

    /**
     * 1. Open players page
     * 2-3. Find poker player by username
     * 4. Click on edit button with poker player with username
     * 5. Copy from edit player page fields in poker player
     * 6. Open players page
     * 7. Find poker player who was copy
     * 8. Generate new password and login name
     * 9. Insert new player
     */
    @Test(dependsOnGroups = INSERT_GROUP)
    public void copyUser(){
        playersPage.open();
        //TODO way to find username
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        playersPage.clickOnEditButton(pokerPlayer.getUsername());
        pokerPlayer = editPlayerPage.getPokerPlayerFromEditForm();
        playersPage.open();
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        Assert.assertEquals(pokerPlayer!=null, true, POKER_PLAYER_NOT_CREATED);
        playersPage.insertPlayerClone(playersPage, pokerPlayer, editPlayerPage);
    }

    /**
     *
     * 1. Open player page
     * 2. Verify if exist stored player in program
     * 3. Find player by username what have insert before in unit test (TODO need find another way)
     * 4. Click on edit button (icon in right of the middle border on players page)
     * 5. Copy fields into poker player from program
     * 6. Verify if exist stored player in program
     */
    @Test(groups = INSERT_GROUP)
    public void readUser(){
        playersPage.open();
        Assert.assertEquals(pokerPlayer!=null, true, POKER_PLAYER_NOT_CREATED);
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        playersPage.clickOnEditButton(pokerPlayer.getUsername());
        pokerPlayer = editPlayerPage.getPokerPlayerFromEditForm();
        Assert.assertEquals(pokerPlayer!=null, true, ERROR_READING_FIELDS);
        //playersPage.readUser();
    }

    /**
     * TODO maybe save in file on serialize, inserted, or copied players
     */
    @AfterTest()
    public void afterTest(){
//        loginTests.afterTest();
//        try {
//            FileUtils.writeStringToFile(new File(PATH_TO_FILE_POKERPLAYERS), pokerPlayer.toString()+"\n",true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
