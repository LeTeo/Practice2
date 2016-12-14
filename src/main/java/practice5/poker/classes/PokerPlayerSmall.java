package practice5.poker.classes;

import org.apache.commons.lang3.RandomStringUtils;
import practice5.poker.interfaces.IPokerPlayerSmall;

/**
 * Created by ly0w on 03.12.2016.
 * Include only 2 fields
 */
@SuppressWarnings("WeakerAccess")
public class PokerPlayerSmall implements IPokerPlayerSmall {
    //length for random
    public static Integer SizeForRandom;
    static {
        SizeForRandom = 8;
    }

    protected String username;
    protected String password;

    public PokerPlayerSmall(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public PokerPlayerSmall(){

    }

    @SuppressWarnings("unused")
    public PokerPlayerSmall(String username) {
        this(username,null);
    }

    public static String RandomUsername(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String RandomUsername() {
        return RandomUsername(SizeForRandom);
    }

    public static String RandomPassword(int count){
        return RandomStringUtils.randomAlphanumeric(count);
    }

    public static String RandomPassword(){
        return RandomPassword(SizeForRandom);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PokerPlayer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PokerPlayerSmall that = (PokerPlayerSmall) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
