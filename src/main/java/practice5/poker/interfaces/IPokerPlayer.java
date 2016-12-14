package practice5.poker.interfaces;

/**
 * Created by ly0w on 02.12.2016.
 * Poker player interface
 */

public interface IPokerPlayer {
    IPokerPlayer RandomFields();

    String getUsername();

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);

    String getFirstName();
    @SuppressWarnings("unused")
    void setFirstName(String firstName);

    String getLastName();
    @SuppressWarnings("unused")
    void setLastName(String lastName);

    String getCity();

    void setCity(String city);

    String getAddress();

    void setAddress(String address);

    String getPhone();

    void setPhone(String phone);

    String getPassword();

    void setPassword(String password);

    String getConfirmPassword();
    @SuppressWarnings("unused")
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
