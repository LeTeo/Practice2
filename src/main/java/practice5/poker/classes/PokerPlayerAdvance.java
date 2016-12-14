package practice5.poker.classes;

import practice5.poker.interfaces.IPokerPlayerAdvance;

/**
 * Created by ly0w on 01.12.2016.
 * Include more fields than Poker Player
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class PokerPlayerAdvance extends PokerPlayer implements IPokerPlayerAdvance {

    private String playerBalance;
    private String state;
    private String lastLogin;
    private String showDeleted;
    private String regDateFrom;
    private String regDateTill;
    private String externalIPAddress;
    private String vipLevel;
    private String βCommunity;
    private String macAddress;

    @SuppressWarnings("unused")
    public PokerPlayerAdvance(String username, String password, String confirmPassword, String email, String first_Name, String last_Name, String city, String address, String phone, String country, String player_Balance, String state, String last_Login, String show_Deleted, String reg_Date_from, String reg_Date_till, String external_IP_Address, String vipLevel, String βCommunity, String macAddress) {
        super(username, password, confirmPassword, email, first_Name, last_Name, city, address, phone, country);
        playerBalance = player_Balance;
        this.state = state;
        lastLogin = last_Login;
        showDeleted = show_Deleted;
        regDateFrom = reg_Date_from;
        regDateTill = reg_Date_till;
        externalIPAddress = external_IP_Address;
        this.vipLevel = vipLevel;
        this.βCommunity = βCommunity;
        this.macAddress = macAddress;

    }

    public String getPlayerBalance() {
        return playerBalance;
    }

    public void setPlayerBalance(String playerBalance) {
        this.playerBalance = playerBalance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getShowDeleted() {
        return showDeleted;
    }

    public void setShowDeleted(String showDeleted) {
        this.showDeleted = showDeleted;
    }

    public String getRegDateFrom() {
        return regDateFrom;
    }

    public void setRegDateFrom(String regDateFrom) {
        this.regDateFrom = regDateFrom;
    }

    public String getRegDateTill() {
        return regDateTill;
    }

    public void setRegDateTill(String regDateTill) {
        this.regDateTill = regDateTill;
    }

    public String getExternalIPAddress() {
        return externalIPAddress;
    }

    public void setExternalIPAddress(String externalIPAddress) {
        this.externalIPAddress = externalIPAddress;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getβCommunity() {
        return βCommunity;
    }

    public void setβCommunity(String βCommunity) {
        this.βCommunity = βCommunity;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String toString() {
        return "PokerPlayer{" +
                "playerBalance='" + playerBalance + '\'' +
                ", state='" + state + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", showDeleted='" + showDeleted + '\'' +
                ", regDateFrom='" + regDateFrom + '\'' +
                ", regDateTill='" + regDateTill + '\'' +
                ", externalIPAddress='" + externalIPAddress + '\'' +
                ", vipLevel='" + vipLevel + '\'' +
                ", βCommunity='" + βCommunity + '\'' +
                ", macAddress='" + macAddress + '\'' +
                "} " + super.toString();
    }

    @SuppressWarnings("SimplifiableIfStatement")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokerPlayerAdvance)) return false;
        if (!super.equals(o)) return false;

        PokerPlayerAdvance that = (PokerPlayerAdvance) o;

        if (getPlayerBalance() != null ? !getPlayerBalance().equals(that.getPlayerBalance()) : that.getPlayerBalance() != null)
            return false;
        if (getState() != null ? !getState().equals(that.getState()) : that.getState() != null) return false;
        if (getLastLogin() != null ? !getLastLogin().equals(that.getLastLogin()) : that.getLastLogin() != null)
            return false;
        if (getShowDeleted() != null ? !getShowDeleted().equals(that.getShowDeleted()) : that.getShowDeleted() != null)
            return false;
        if (getRegDateFrom() != null ? !getRegDateFrom().equals(that.getRegDateFrom()) : that.getRegDateFrom() != null)
            return false;
        if (getRegDateTill() != null ? !getRegDateTill().equals(that.getRegDateTill()) : that.getRegDateTill() != null)
            return false;
        if (getExternalIPAddress() != null ? !getExternalIPAddress().equals(that.getExternalIPAddress()) : that.getExternalIPAddress() != null)
            return false;
        if (getVipLevel() != null ? !getVipLevel().equals(that.getVipLevel()) : that.getVipLevel() != null)
            return false;
        if (getβCommunity() != null ? !getβCommunity().equals(that.getβCommunity()) : that.getβCommunity() != null)
            return false;
        return getMacAddress() != null ? getMacAddress().equals(that.getMacAddress()) : that.getMacAddress() == null;

    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getPlayerBalance() != null ? getPlayerBalance().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getLastLogin() != null ? getLastLogin().hashCode() : 0);
        result = 31 * result + (getShowDeleted() != null ? getShowDeleted().hashCode() : 0);
        result = 31 * result + (getRegDateFrom() != null ? getRegDateFrom().hashCode() : 0);
        result = 31 * result + (getRegDateTill() != null ? getRegDateTill().hashCode() : 0);
        result = 31 * result + (getExternalIPAddress() != null ? getExternalIPAddress().hashCode() : 0);
        result = 31 * result + (getVipLevel() != null ? getVipLevel().hashCode() : 0);
        result = 31 * result + (getβCommunity() != null ? getβCommunity().hashCode() : 0);
        result = 31 * result + (getMacAddress() != null ? getMacAddress().hashCode() : 0);
        return result;
    }
}
