package test.testng;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {

    @BeforeClass
    public void beforeClassLogin() {
        System.out.println("RUN BEFORE CLASS LOGIN");
    }

    @BeforeTest
    public void beforeTestLogin() {
        System.out.println("RUN BEFORE LOGIN TEST");
    }

    @BeforeMethod
    public void beforeLogin() {
        System.out.println("RUN BEFORE METHOD LOGIN TEST");
    }

    @Test
    public void loginCorrectCred () {
        System.out.println("Login correct credential test");
    }

    @Test
    public void loginInCorrectCred () {
        System.out.println("Login in correct credential test");
    }
}
