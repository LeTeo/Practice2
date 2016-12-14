package practice5.poker.interfaces.pages;

import practice5.poker.interfaces.IPokerPlayer;

/**
 * Created by ly0w on 04.12.2016.
 * Include const fields
 */
public interface IEditPlayerPage {
    String USERNAME_ID = "ff14642ac1c__us_login";
    String EMAIL_ID = "ff14642ac1c__us_email";
    String FIRST_NAME_ID = "ff14642ac1c__us_fname";
    String LAST_NAME_ID = "ff14642ac1c__us_lname";
    String CITY_ID = "ff14642ac1c__us_city";
    String ADDRESS_ID = "ff14642ac1c__us_address";
    String PHONE_ID = "ff14642ac1c__us_phone";
    String COUNTRY_ID = "ff14642ac1c__us_country";
    String URL = "http://80.92.229.236:81/players/edit/";
    String TITLE = "Players - Edit";
    String BUTTON_SAVE = "button_save";
    String BUTTON_CANCEL = "button_cancel";
    String VALUE = "value";
    @SuppressWarnings("unused")
    String CANCEL_ALERT_MESSAGE = "Do you really want to cancel? All changes will be lost";
    @SuppressWarnings("unused")
    void editPokerPlayerAndSave(IPokerPlayer pokerPlayer);

    void editPokerPlayerAndSave(IPokerPlayer pokerPlayer, IPokerPlayer pokerPlayerFromEditForm);

    void editPokerPlayerAndSaveAfterInsert(IPokerPlayer pokerPlayer);

    void editPokerPlayerAndSaveAfterInsert(IPokerPlayer pokerPlayer, IPokerPlayer pokerPlayerFromEditForm);

    void editPokerPlayerAndSaveWithoutCorrectErrors(IPokerPlayer pokerPlayer);

    void clickOnButtonSave();
    @SuppressWarnings("unused")
    void clickOnButtonCancel();

    //Correct errors on EDIT_FORM
    IPokerPlayer editPokerPlayerIntoFormWithCorrectErrors(IPokerPlayer pokerPlayer, String actualCountry);

    IPokerPlayer getPokerPlayerFromEditForm();
}
