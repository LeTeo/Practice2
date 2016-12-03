package practice2;

/**
 * Created by ly0w on 02.12.2016.
 */
public interface IPokerPlayerAdvance {
    String getPlayer_Balance();

    void setPlayer_Balance(String player_Balance);

    String getState();

    void setState(String state);

    String getLast_Login();

    void setLast_Login(String last_Login);

    String getShow_Deleted();

    void setShow_Deleted(String show_Deleted);

    String getReg_Date_from();

    void setReg_Date_from(String reg_Date_from);

    String getReg_Date_till();

    void setReg_Date_till(String reg_Date_till);

    String getExternal_IP_Address();

    void setExternal_IP_Address(String external_IP_Address);

    String getVIP_Level();

    void setVIP_Level(String VIP_Level);

    String getΒ_Community();

    void setΒ_Community(String β_Community);

    String getMAC_Address();

    void setMAC_Address(String MAC_Address);

    @Override
    String toString();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
