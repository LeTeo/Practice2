package practice5.poker.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Admin on 07.12.2016.
 */
public class ExampleTests {

    @Test
    public void loginTest() {
        Assert.assertEquals(1, 2);
    }

    @Test(dependsOnMethods = "loginTest", alwaysRun = true)
    public void loadDataTest() {
        Assert.assertEquals(1, 1);
    }

    @Test(dependsOnMethods = "loadDataTest", alwaysRun = true)
    public void logoutTest() {
        Assert.assertEquals(1, 1);
    }
}
