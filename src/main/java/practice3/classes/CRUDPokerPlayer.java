package practice3.classes;

import practice3.interfaces.IPokerPlayer;


/**
 * Created by ly0w on 02.12.2016.
 * CRUD for poker player
 */
public class CRUDPokerPlayer {
/*
    Hashtable <String,IPokerPlayer> pokerPlayers;

    public static IPokerPlayer createRandomPokerPlayer(){
        return new PokerPlayer().RandomFields();
    }

    public static IPokerPlayer editPokerPlayer(IPokerPlayer pokerPlayer){
        return pokerPlayer;
    }

    public static IPokerPlayer editPokerPlayer(String username, String password, String confirmPassword, String email, String first_Name, String last_Name, String city, String address, String phone, String country){
        return new PokerPlayer(username, password, confirmPassword, email, first_Name, last_Name, city, address, phone, country);
    }
*/
    public static IPokerPlayer createPokerPlayerWithEmptyFields(){
        return new PokerPlayer("","","","","","","","","","");
    }

    public static IPokerPlayer editPokerPlayer(IPokerPlayer oldPokerPlayer, IPokerPlayer newPokerPlayer){
        newPokerPlayer.setUsername(oldPokerPlayer.getUsername());
        return newPokerPlayer;
    }
}
