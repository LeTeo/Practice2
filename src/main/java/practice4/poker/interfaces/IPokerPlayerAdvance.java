package practice4.poker.interfaces;

/**
 * Created by ly0w on 02.12.2016.
 * For poker player advance
 */
@SuppressWarnings("unused")
public interface IPokerPlayerAdvance {
    String getPlayerBalance();

    void setPlayerBalance(String playerBalance);

    String getState();

    void setState(String state);

    String getLastLogin();

    void setLastLogin(String lastLogin);

    String getShowDeleted();

    void setShowDeleted(String showDeleted);

    String getRegDateFrom();

    void setRegDateFrom(String regDateFrom);

    String getRegDateTill();

    void setRegDateTill(String regDateTill);

    String getExternalIPAddress();

    void setExternalIPAddress(String externalIPAddress);

    String getVipLevel();

    void setVipLevel(String vipLevel);

    String getβCommunity();

    void setβCommunity(String βCommunity);

    String getMacAddress();

    void setMacAddress(String macAddress);

    @Override
    String toString();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
