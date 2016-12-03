package practice2;

/**
 * Created by ly0w on 01.12.2016.
 */
public class PokerPlayerAdvance extends PokerPlayer implements IPokerPlayerAdvance {

    private String Player_Balance;
    private String State;
    private String Last_Login;
    private String Show_Deleted;
    private String Reg_Date_from;
    private String Reg_Date_till;
    private String External_IP_Address;
    private String VIP_Level;
    private String β_Community;
    private String MAC_Address;

    public PokerPlayerAdvance(String username, String password, String confirmPassword, String email, String first_Name, String last_Name, String city, String address, String phone, String country, String player_Balance, String state, String last_Login, String show_Deleted, String reg_Date_from, String reg_Date_till, String external_IP_Address, String VIP_Level, String β_Community, String MAC_Address) {
        super(username, password, confirmPassword, email, first_Name, last_Name, city, address, phone, country);
        Player_Balance = player_Balance;
        State = state;
        Last_Login = last_Login;
        Show_Deleted = show_Deleted;
        Reg_Date_from = reg_Date_from;
        Reg_Date_till = reg_Date_till;
        External_IP_Address = external_IP_Address;
        this.VIP_Level = VIP_Level;
        this.β_Community = β_Community;
        this.MAC_Address = MAC_Address;

    }

    public String getPlayer_Balance() {
        return Player_Balance;
    }

    public void setPlayer_Balance(String player_Balance) {
        Player_Balance = player_Balance;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getLast_Login() {
        return Last_Login;
    }

    public void setLast_Login(String last_Login) {
        Last_Login = last_Login;
    }

    public String getShow_Deleted() {
        return Show_Deleted;
    }

    public void setShow_Deleted(String show_Deleted) {
        Show_Deleted = show_Deleted;
    }

    public String getReg_Date_from() {
        return Reg_Date_from;
    }

    public void setReg_Date_from(String reg_Date_from) {
        Reg_Date_from = reg_Date_from;
    }

    public String getReg_Date_till() {
        return Reg_Date_till;
    }

    public void setReg_Date_till(String reg_Date_till) {
        Reg_Date_till = reg_Date_till;
    }

    public String getExternal_IP_Address() {
        return External_IP_Address;
    }

    public void setExternal_IP_Address(String external_IP_Address) {
        External_IP_Address = external_IP_Address;
    }

    public String getVIP_Level() {
        return VIP_Level;
    }

    public void setVIP_Level(String VIP_Level) {
        this.VIP_Level = VIP_Level;
    }

    public String getΒ_Community() {
        return β_Community;
    }

    public void setΒ_Community(String β_Community) {
        this.β_Community = β_Community;
    }

    public String getMAC_Address() {
        return MAC_Address;
    }

    public void setMAC_Address(String MAC_Address) {
        this.MAC_Address = MAC_Address;
    }

    public String toString() {
        return "PokerPlayerAdvance{" +
                "Player_Balance='" + Player_Balance + '\'' +
                ", State='" + State + '\'' +
                ", Last_Login='" + Last_Login + '\'' +
                ", Show_Deleted='" + Show_Deleted + '\'' +
                ", Reg_Date_from='" + Reg_Date_from + '\'' +
                ", Reg_Date_till='" + Reg_Date_till + '\'' +
                ", External_IP_Address='" + External_IP_Address + '\'' +
                ", VIP_Level='" + VIP_Level + '\'' +
                ", β_Community='" + β_Community + '\'' +
                ", MAC_Address='" + MAC_Address + '\'' +
                "} " + super.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokerPlayerAdvance)) return false;
        if (!super.equals(o)) return false;

        PokerPlayerAdvance that = (PokerPlayerAdvance) o;

        if (getPlayer_Balance() != null ? !getPlayer_Balance().equals(that.getPlayer_Balance()) : that.getPlayer_Balance() != null)
            return false;
        if (getState() != null ? !getState().equals(that.getState()) : that.getState() != null) return false;
        if (getLast_Login() != null ? !getLast_Login().equals(that.getLast_Login()) : that.getLast_Login() != null)
            return false;
        if (getShow_Deleted() != null ? !getShow_Deleted().equals(that.getShow_Deleted()) : that.getShow_Deleted() != null)
            return false;
        if (getReg_Date_from() != null ? !getReg_Date_from().equals(that.getReg_Date_from()) : that.getReg_Date_from() != null)
            return false;
        if (getReg_Date_till() != null ? !getReg_Date_till().equals(that.getReg_Date_till()) : that.getReg_Date_till() != null)
            return false;
        if (getExternal_IP_Address() != null ? !getExternal_IP_Address().equals(that.getExternal_IP_Address()) : that.getExternal_IP_Address() != null)
            return false;
        if (getVIP_Level() != null ? !getVIP_Level().equals(that.getVIP_Level()) : that.getVIP_Level() != null)
            return false;
        if (getΒ_Community() != null ? !getΒ_Community().equals(that.getΒ_Community()) : that.getΒ_Community() != null)
            return false;
        return getMAC_Address() != null ? getMAC_Address().equals(that.getMAC_Address()) : that.getMAC_Address() == null;

    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getPlayer_Balance() != null ? getPlayer_Balance().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getLast_Login() != null ? getLast_Login().hashCode() : 0);
        result = 31 * result + (getShow_Deleted() != null ? getShow_Deleted().hashCode() : 0);
        result = 31 * result + (getReg_Date_from() != null ? getReg_Date_from().hashCode() : 0);
        result = 31 * result + (getReg_Date_till() != null ? getReg_Date_till().hashCode() : 0);
        result = 31 * result + (getExternal_IP_Address() != null ? getExternal_IP_Address().hashCode() : 0);
        result = 31 * result + (getVIP_Level() != null ? getVIP_Level().hashCode() : 0);
        result = 31 * result + (getΒ_Community() != null ? getΒ_Community().hashCode() : 0);
        result = 31 * result + (getMAC_Address() != null ? getMAC_Address().hashCode() : 0);
        return result;
    }
}
