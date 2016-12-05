package practice3.interfaces.pages;

import practice3.interfaces.IPokerPlayer;
import practice3.pages.PlayersPage;

/**
 * Created by ly0w on 04.12.2016.
 */
public interface IPlayersPage {
    IPokerPlayer insertPlayerClone(PlayersPage playersPage, IPokerPlayer pokerPlayer, IEditPlayerPage editPlayerPage);
}
