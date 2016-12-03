package practice3.classes;

import practice3.interfaces.IPokerPlayer;

import java.util.Hashtable;


/**
 * Created by ly0w on 02.12.2016.
 */
public class CRUDPokerPlayer {

    Hashtable <String,String> pokerPlayers;

    public static IPokerPlayer createRandomPokerPlayer(){
        return new PokerPlayer().RandomFields();
    }

    public static IPokerPlayer editPokerPlayer(IPokerPlayer pokerPlayer){
        return pokerPlayer;
    }

    public static IPokerPlayer editPokerPlayer(String username, String password, String confirmPassword, String email, String first_Name, String last_Name, String city, String address, String phone, String country){
        return new PokerPlayer(username, password, confirmPassword, email, first_Name, last_Name, city, address, phone, country);
    }
}
