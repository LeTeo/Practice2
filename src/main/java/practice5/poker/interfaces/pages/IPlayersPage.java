package practice5.poker.interfaces.pages;

import practice5.poker.interfaces.IPokerPlayer;
import practice5.poker.pages.PlayersPage;

/**
 * Created by ly0w on 06.12.2016.
 * Interface include const
 */
public interface IPlayersPage {
    String TITLE = "Players";
    String URL = "http://80.92.229.236:81/players";
    String XPATH_PLAYERS_INSERT = ".//a[@href='http://80.92.229.236:81/players/insert']";
    String USERNAME_FIELD_ID = "723a925886__login";
    String XPATH_INPUT_SEARCH = ".//input[@VALUE='Search']";
    String XPATH_FIND_USERNAME_EDIT_OPEN = ".//tr[.//a[text()='";
    String XPATH_FIND_USERNAME_EDIT_CLOSE = "']]//img[@title='Edit']";
    String XPATH_FIND_USERNAME_DELETE_OPEN = ".//tr[.//a[text()='";
    String XPATH_FIND_USERNAME_DELETE_CLOSE = "']]//img[@title='Delete']";
    String XPATH_BUTTON_RESET = ".//input[@name='reset']";
    @SuppressWarnings("unused")
    String XPATH_PERSON_ID_SORT_ASC_CLICK = ".//a[@href='http://80.92.229.236:81/players/index/sort/us_person_id/order/ASC']";
    String XPATH_USERNAME_TOP = ".//a[contains(@href,\"javascript:_popUp('http://80.92.229.236:81/players/edit/popup/1/readonly/1/id/enc\")][position()=1]";
    String XPATH_EMAIL_TOP = ".//a[contains(@href,'mailto:')][position()=1]";
    String XPATH_DELETE_SUCCESS = ".//div[@class='datagrid_flashmessagespanel_container']/ul";
    String VALUE = "value";
    String DELETE_MESSAGE = "Player has been deleted";
    String EMAIL_FIELD_ID = "723a925886__email";
    String URL_SORT_PLAYERS_BY_INDEX_DESC = "http://80.92.229.236:81/players/index/sort/us_person_id/order/DESC";

    void open();

    void clickOnInsertButton();

    void clickOnResetButton();

    void clickOnEditButton(String username);

    void findPlayerByUsername(String username);

    void openEditFormByUsername(String username);

    void clickOnDeleteButton(String username);

    void findPlayerByUsernameAndEmail(String username, String email);

    String getDeleteMessage();

    IPokerPlayer insertPlayerClone(PlayersPage playersPage, IPokerPlayer pokerPlayer, IEditPlayerPage editPlayerPage);

    IPokerPlayer findUserPokerPlayerNameAndEmailLastCreatedUserById();

    void findPlayerByUsernameAndEmail(IPokerPlayer readPokerPlayer);
}
