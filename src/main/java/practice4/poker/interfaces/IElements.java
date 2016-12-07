package practice4.poker.interfaces;

/**
 * Created by ly0w on 03.12.2016.
 * Elements interface
 */
public interface IElements extends IPokerPlayer {
    
    String getUsername();

    
    String getEmail();

    
    String getFirstName();

    
    String getLastName();

    
    String getCity();

    
    String getAddress();

    
    String getPhone();

    
    String getPassword();

    
    String getConfirmPassword();

    
    String getCountry();

    
    boolean equals(Object o);

    
    int hashCode();

    
    String toString();
}
