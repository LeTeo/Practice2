package practice4.poker.interfaces.pages;

import practice4.poker.classes.Elements;
import practice4.poker.interfaces.IElements;
import practice4.poker.interfaces.IPokerPlayer;

/**
 * Created by ly0w on 04.12.2016.
 * Include const fields
 */
public interface IEditPlayerPage {
    IElements EDIT_FORM = new Elements(
            "ff14642ac1c__us_login","ff14642ac1c__us_email","ff14642ac1c__us_fname",
            "ff14642ac1c__us_lname","ff14642ac1c__us_city","ff14642ac1c__us_address","ff14642ac1c__us_phone", "ff14642ac1c__us_country");
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
