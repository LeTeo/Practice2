package practice3.classes;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by ly0w on 03.12.2016.
 * Include only 2 fields
 */
@SuppressWarnings("WeakerAccess")
public class PokerPlayerSmall implements practice3.interfaces.IPokerPlayerSmall {
    //length for random
    public static Integer SizeForRandom;
    static {
        SizeForRandom = 8;
    }

    protected String username;
    protected String password;

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

    public PokerPlayerSmall(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @SuppressWarnings("unused")
    public PokerPlayerSmall(String username) {
        this(username,null);
    }

    public PokerPlayerSmall(){

    }
}
