package practice3.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import practice3.classes.PokerPlayer;
import practice3.interfaces.pages.IEditPlayerPage;
import practice3.interfaces.pages.IInsertPlayerPage;
import practice3.interfaces.pages.IPlayersPage;
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

    private static final String INSERT_GROUP = "Insert";
    private static final String DELETE_GROUP = "Delete";
    private static final String PATH_TO_FILE_POKERPLAYERS = "pokerPlayers.txt";


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
    private static final String ERROR_READING_FIELDS = "ERROR_READING_FIELDS";


    private WebDriver driver; // Declare var
    private PlayersPage playersPage;
    private LoginTests loginTests;
    private IInsertPlayerPage IInsertPlayerPage;
    private IEditPlayerPage IEditPlayerPage;
    private IPokerPlayer pokerPlayer;


    private void setLoginTestsDriver() {
        setDriver(loginTests.getDriver());
    }

    public void setDriver(WebDriver driver) {
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
        IInsertPlayerPage = new InsertPlayerPage(driver);
        IEditPlayerPage = new EditPlayerPage(driver);
    }

    @BeforeMethod(groups = INSERT_GROUP)
    public void beforeMethod(){

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
        pokerPlayer = IInsertPlayerPage.insertRandomPokerPlayer();
        Assert.assertEquals(driver.getTitle(), PlayersPage.TITLE, WRONG_TITLE);
        Assert.assertEquals(driver.getCurrentUrl(), PlayersPage.URL, YOU_ARE_NOT_ON_INSERT_PAGE);
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        playersPage.openEditFormByUsername(pokerPlayer.getUsername());
        Assert.assertEquals(driver.getTitle(), IEditPlayerPage.TITLE, WRONG_TITLE);
        IPokerPlayer pokerPlayerFromEditForm = IEditPlayerPage.getPokerPlayerFromEditForm();
        assertFieldsWithErrors(pokerPlayerFromEditForm, pokerPlayer);
        IEditPlayerPage.editPokerPlayerAndSave(pokerPlayer,pokerPlayerFromEditForm);
        playersPage.openEditFormByUsername(pokerPlayer.getUsername());
        pokerPlayerFromEditForm = IEditPlayerPage.getPokerPlayerFromEditForm();
        assertFields(pokerPlayerFromEditForm, pokerPlayer);
        //Вернутся к странице Players и проверить
        //editPlayerPage.clickOnButtonCancel();
        //Assert.assertEquals(driver.getTitle(), PlayersPage.TITLE, WRONG_TITLE);
        //Assert.assertEquals(driver.getCurrentUrl(), PlayersPage.URL, YOU_ARE_NOT_ON_PLAYERS_PAGE);
    }

    @Test
    public void insertUserNegativeAlertCanceAccept(){
        playersPage.open();
        playersPage.clickOnInsertButton();
        Assert.assertEquals(driver.getTitle(), IInsertPlayerPage.TITLE, WRONG_TITLE);
        Assert.assertEquals(driver.getCurrentUrl(), IInsertPlayerPage.URL, YOU_ARE_NOT_ON_INSERT_PAGE);
        pokerPlayer = IInsertPlayerPage.insertRandomPokerPlayerAndClickButtonCancel();
        Alert alertCancel = driver.switchTo().alert();
        Assert.assertEquals(alertCancel.getText(), IInsertPlayerPage.CANCEL_ALERT_MESSAGE,WRONG_MODAL_DIALOG);
        alertCancel.accept();
        Assert.assertEquals(driver.getTitle(), IInsertPlayerPage.TITLE,WRONG_TITLE);
    }

    @Test
    public void insertUserNegativeAlertCanceDismiss(){
        playersPage.open();
        playersPage.clickOnInsertButton();
        Assert.assertEquals(driver.getTitle(), IInsertPlayerPage.TITLE, WRONG_TITLE);
        Assert.assertEquals(driver.getCurrentUrl(), IInsertPlayerPage.URL, YOU_ARE_NOT_ON_INSERT_PAGE);
        pokerPlayer = IInsertPlayerPage.insertRandomPokerPlayerAndClickButtonCancel();
        Alert alertCancel = driver.switchTo().alert();
        Assert.assertEquals(alertCancel.getText(), IInsertPlayerPage.CANCEL_ALERT_MESSAGE,WRONG_MODAL_DIALOG);
        alertCancel.dismiss();
        Assert.assertEquals(driver.getTitle(), PlayersPage.TITLE ,WRONG_TITLE);
    }

    private void assertFieldsWithErrors(IPokerPlayer actualPokerPlayer, IPokerPlayer expectedPokerPlayer) {
        Assert.assertEquals(actualPokerPlayer.getEmail(), expectedPokerPlayer.getEmail(), WRONG_EMAIL);
        Assert.assertEquals(actualPokerPlayer.getFirstName(), expectedPokerPlayer.getLastName(), WRONG_FIRST_NAME);
        Assert.assertEquals(actualPokerPlayer.getLastName(), expectedPokerPlayer.getFirstName(), WRONG_LAST_NAME);
        Assert.assertEquals(actualPokerPlayer.getCity(), expectedPokerPlayer.getCity(), WRONG_CITY);
        Assert.assertEquals(actualPokerPlayer.getAddress(), "", WRONG_ADDRESS);
        Assert.assertEquals(actualPokerPlayer.getPhone(), expectedPokerPlayer.getPhone(), WRONG_PHONE);
    }

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
     * 6.
     */
    @Test(dependsOnGroups = INSERT_GROUP)
    public void updateUser(){
        playersPage.open();
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        playersPage.clickOnEditButton(pokerPlayer.getUsername());
        Assert.assertEquals(driver.getTitle(), IEditPlayerPage.TITLE, WRONG_TITLE);
        Assert.assertEquals(driver.getCurrentUrl().substring(0, IEditPlayerPage.URL.length()), IEditPlayerPage.URL, YOU_ARE_NOT_ON_EDIT_PAGE);
        //editPlayerPage.editPokerPlayerAndSave(pokerPlayer,editPlayerPage.getPokerPlayerFromEditForm());
        //Assert.assertEquals(driver.getTitle(), PlayersPage.TITLE, WRONG_TITLE);
        //Assert.assertEquals(driver.getCurrentUrl(), PlayersPage.URL, YOU_ARE_NOT_ON_INSERT_PAGE);
    }

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

    @Test(dependsOnGroups = INSERT_GROUP)
    public void copyUser(){
        playersPage.open();
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        playersPage.clickOnEditButton(pokerPlayer.getUsername());
        pokerPlayer = IEditPlayerPage.getPokerPlayerFromEditForm();
        playersPage.open();
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        Assert.assertEquals(pokerPlayer!=null, true, ERROR_READING_FIELDS);
    }

    @Test(groups = INSERT_GROUP)
    public void readUser(){
        playersPage.open();
        playersPage.findPlayerByUsername(pokerPlayer.getUsername());
        playersPage.clickOnEditButton(pokerPlayer.getUsername());
        pokerPlayer = IEditPlayerPage.getPokerPlayerFromEditForm();
        Assert.assertEquals(pokerPlayer!=null, true, ERROR_READING_FIELDS);
        //playersPage.readUser();
    }

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
