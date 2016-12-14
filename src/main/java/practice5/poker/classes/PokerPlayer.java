package practice5.poker.classes;

import org.apache.commons.lang3.RandomStringUtils;
import practice5.poker.interfaces.IPokerPlayer;

/**
 * Created by ly0w on 28.11.2016.
 *
 *          PokerPlayerSmall (IPokerPlayerSmall)
 *                  |
 *             PokerPlayer (IPokerPlayer)
 *                  |
 *          PokerPlayerAdvance (IPokerPlayerAdvance)
 */
public class PokerPlayer extends PokerPlayerSmall implements IPokerPlayer {

    @SuppressWarnings("WeakerAccess")
    public static String MailDomenForRandom = "@gmail.com"; //length for random


    @SuppressWarnings("WeakerAccess")
    protected String confirmPassword;
    @SuppressWarnings("WeakerAccess")
	protected String email;
    @SuppressWarnings("WeakerAccess")
	protected String firstName;
    @SuppressWarnings("WeakerAccess")
	protected String lastName;
    @SuppressWarnings("WeakerAccess")
    protected String city;
    @SuppressWarnings("WeakerAccess")
    protected String address;
    @SuppressWarnings("WeakerAccess")
    protected String phone;
    @SuppressWarnings("WeakerAccess")
    protected String country;

    public PokerPlayer(){
        super();
    }

    //String username, String password, String confirmPassword, String email, String first_Name, String last_Name, String city, String address, String phone, String country
    public PokerPlayer(String username, String password, String confirmPassword, String email, String first_Name, String last_Name, String city, String address, String phone, String country) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        firstName = first_Name;
        lastName = last_Name;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.country = country;
    }



    public PokerPlayer(String username, String email, String first_Name, String last_Name, String city, String address, String phone, String country) {
        this(username,null,null,email,first_Name,last_Name,city,address,phone,country);
    }

    public IPokerPlayer RandomFields(){

        String username = RandomUsername();
        this.username = username;
        String password = RandomPassword();
        this.password = password;
        this.confirmPassword = password;
        email = username + MailDomenForRandom;
        firstName = username.substring(0, username.length()/2); //First Part username
        lastName = username.substring(username.length()/2, username.length());//last part username
        city = RandomStringUtils.randomAlphabetic(SizeForRandom);
        address = RandomStringUtils.randomAlphabetic(SizeForRandom);
        phone = RandomStringUtils.randomNumeric(7);
        country = RandomStringUtils.randomAlphabetic(SizeForRandom);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokerPlayer)) return false;

        PokerPlayer that = (PokerPlayer) o;

        if (getConfirmPassword() != null ? !getConfirmPassword().equals(that.getConfirmPassword()) : that.getConfirmPassword() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null) return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) return false;
        if (getPhone() != null ? !getPhone().equals(that.getPhone()) : that.getPhone() != null) return false;
        return getCountry() != null ? getCountry().equals(that.getCountry()) : that.getCountry() == null;

    }

    @Override
    public int hashCode() {
        int result = getConfirmPassword() != null ? getConfirmPassword().hashCode() : 0;
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        return result;
    }

    public String toString() {
        return "PokerPlayer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
