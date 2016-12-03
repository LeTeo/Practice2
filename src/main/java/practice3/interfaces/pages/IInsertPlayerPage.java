package practice3.interfaces.pages;

import practice3.classes.Elements;
import practice3.interfaces.IElements;
import practice3.interfaces.IPokerPlayer;

/**
 * Created by ly0w on 04.12.2016.
 */
public interface IInsertPlayerPage {
    IElements INSERT_FORM = new Elements(
            "ff14642ac1c__us_login", "ff14642ac1c__us_password","ff14642ac1c__confirm_password","ff14642ac1c__us_email","ff14642ac1c__us_fname",
            "ff14642ac1c__us_lname","ff14642ac1c__us_city","ff14642ac1c__us_address","ff14642ac1c__us_phone", "ff14642ac1c__us_country");
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
