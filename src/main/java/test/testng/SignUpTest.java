package test.testng;

import org.testng.annotations.Test;

public class SignUpTest {

    @Test
    public void signUpCorrectCred () {
        System.out.println("signUp correct credential test");
    }

    @Test
    public void signUpInCorrectCred () {
        System.out.println("signUp in correct credential test");
    }
}
