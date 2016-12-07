package practice4.poker.classes;

import practice4.poker.interfaces.IElements;

/**
 * Created by ly0w on 03.12.2016.
 * Elements for insert update fields
 */
public class Elements extends PokerPlayer implements IElements {
    public Elements(String username, String password, String confirmPassword, String email, String first_Name, String last_Name, String city, String address, String phone, String country) {
        super(username, password, confirmPassword, email, first_Name, last_Name, city, address, phone, country);
    }

    public Elements(String username, String email, String first_Name, String last_Name, String city, String address, String phone, String country) {
        super(username, email, first_Name, last_Name, city, address, phone, country);
    }

    public String getUsername() {
        return super.getUsername();
    }

    public String getEmail() {
        return super.getEmail();
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public String getLastName() {
        return super.getLastName();
    }

    public String getCity() {
        return super.getCity();
    }

    public String getAddress() {
        return super.getAddress();
    }

    public String getPhone() {
        return super.getPhone();
    }

    public String getPassword() {
        return super.getPassword();
    }

    public String getConfirmPassword() {
        return super.getConfirmPassword();
    }

    public String getCountry() {
        return super.getCountry();
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString();
    }
}
