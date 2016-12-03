package practice2;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;

/**
 * Created by ly0w on 28.11.2016.
 */
public class PokerPlayer implements IPokerPlayer {

    protected String Username;
    protected String Password;
    protected String ConfirmPassword;
	protected String Email;
	protected String First_Name;
	protected String Last_Name;
    protected String City;
    protected String Address;
    protected String Phone;
    protected String Country;


    public PokerPlayer() {
    }

    //String username, String password, String confirmPassword, String email, String first_Name, String last_Name, String city, String address, String phone, String country
    public PokerPlayer(String username, String password, String confirmPassword, String email, String first_Name, String last_Name, String city, String address, String phone, String country) {
        Username = username;
        Password = password;
        ConfirmPassword = confirmPassword;
        Email = email;
        First_Name = first_Name;
        Last_Name = last_Name;
        City = city;
        Address = address;
        Phone = phone;
        Country = country;


    }

    public PokerPlayer(String username, String email, String first_Name, String last_Name, String city, String address, String phone, String country) {
        Username = username;
        Email = email;
        First_Name = first_Name;
        Last_Name = last_Name;
        City = city;
        Address = address;
        Phone = phone;
        Country = country;
    }

    public PokerPlayer RandomFields(){
        Integer size = 8; //size for random strings
        String username = RandomStringUtils.randomAlphabetic(size);
        Username = username;
        String password = RandomStringUtils.randomAlphanumeric(size);
        Password = password;
        String confirmPassword = password;
        ConfirmPassword = confirmPassword;
        Email = username + "@gmail.com";
        First_Name = username.substring(0, username.length()/2); //First Part username
        Last_Name = username.substring(username.length()/2, username.length());//last part username
        City = RandomStringUtils.randomAlphabetic(size);
        Address = RandomStringUtils.randomAlphabetic(size);
        Phone = RandomStringUtils.randomNumeric(7);
        Country = RandomStringUtils.randomAlphabetic(size);
        return this;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokerPlayer)) return false;

        PokerPlayer that = (PokerPlayer) o;

        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        if (getConfirmPassword() != null ? !getConfirmPassword().equals(that.getConfirmPassword()) : that.getConfirmPassword() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        if (getFirst_Name() != null ? !getFirst_Name().equals(that.getFirst_Name()) : that.getFirst_Name() != null)
            return false;
        if (getLast_Name() != null ? !getLast_Name().equals(that.getLast_Name()) : that.getLast_Name() != null)
            return false;
        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null) return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) return false;
        if (getPhone() != null ? !getPhone().equals(that.getPhone()) : that.getPhone() != null) return false;
        return getCountry() != null ? getCountry().equals(that.getCountry()) : that.getCountry() == null;

    }

    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getConfirmPassword() != null ? getConfirmPassword().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getFirst_Name() != null ? getFirst_Name().hashCode() : 0);
        result = 31 * result + (getLast_Name() != null ? getLast_Name().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        return result;
    }

    public String toString() {
        return "PokerPlayer{" +
                "username='" + Username + '\'' +
                ", password='" + Password + '\'' +
                ", confirmPassword='" + ConfirmPassword + '\'' +
                ", email='" + Email + '\'' +
                ", firstName='" + First_Name + '\'' +
                ", lastName='" + Last_Name + '\'' +
                ", city='" + City + '\'' +
                ", address='" + Address + '\'' +
                ", phone='" + Phone + '\'' +
                ", country='" + Country + '\'' +
                '}';
    }
}
