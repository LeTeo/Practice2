package practice5.poker.interfaces.pages;

import practice5.poker.interfaces.IPokerPlayer;

/**
 * Created by ly0w on 04.12.2016.
 */
public interface IInsertPlayerPage {

    String USERNAME_ID = "ff14642ac1c__us_login";
    String PASSWORD_ID = "ff14642ac1c__us_password";
    String CONFIRM_PASSWORD_ID = "ff14642ac1c__confirm_password";
    String EMAIL_ID = "ff14642ac1c__us_email";
    String FIRST_NAME_ID = "ff14642ac1c__us_fname";
    String LAST_NAME_ID = "ff14642ac1c__us_lname";
    String CITY_ID = "ff14642ac1c__us_city";
    String ADDRESS_ID = "ff14642ac1c__us_address";
    String PHONE_ID = "ff14642ac1c__us_phone";
    String COUNTRY_ID = "ff14642ac1c__us_country";
    String URL = "http://80.92.229.236:81/players/insert";
    String TITLE = "Players - Insert";
    String BUTTON_SAVE = "button_save";
    String BUTTON_CANCEL = "button_cancel";
    String VALUE = "value";
    String CANCEL_ALERT_MESSAGE = "Do you really want to cancel? All changes will be lost";

    IPokerPlayer insertRandomPokerPlayer();

    IPokerPlayer insertPokerPlayer(IPokerPlayer pokerPlayer);

    IPokerPlayer insertRandomPokerPlayerAndClickButtonCancel();

    IPokerPlayer insertPokerPlayerAndClickButtonCancel(IPokerPlayer pokerPlayer);

    void clickOnButtonSave();

    void clickOnButtonCancel();

    //insert PokerPlayer fields into form
    IPokerPlayer insertPokerPlayerIntoForm(IPokerPlayer pokerPlayer);

    IPokerPlayer getPokerPlayerFromInsertedForm();
}
