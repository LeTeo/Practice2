package practice2;

/**
 * Created by ly0w on 02.12.2016.
 */
public interface IPokerPlayer {
    PokerPlayer RandomFields();

    String getUsername();

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);

    String getFirst_Name();

    void setFirst_Name(String first_Name);

    String getLast_Name();

    void setLast_Name(String last_Name);

    String getCity();

    void setCity(String city);

    String getAddress();

    void setAddress(String address);

    String getPhone();

    void setPhone(String phone);

    String getPassword();

    void setPassword(String password);

    String getConfirmPassword();

    void setConfirmPassword(String confirmPassword);

    String getCountry();

    void setCountry(String country);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    @Override
    String toString();
}
