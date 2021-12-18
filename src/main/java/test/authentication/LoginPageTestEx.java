package test.authentication;

import drivers.AndroidDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.BottomNavigationBar;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import server.AppiumServer;
import test.BaseTest;
import test_data.authentication.DataObjectBuilder;
import test_data.authentication.LoginCred;

public class LoginPageTestEx extends BaseTest {

    @Test()
    public void loginWithCorrectCred() {
        AppiumDriver<MobileElement> driver = getDriver();

           LoginPage loginPage = new LoginPage(driver);
           BottomNavigationBar bottomNavigationBar = loginPage.bottomNavigationBar();

           bottomNavigationBar.login().click();

           loginPage.inputEmail().sendKeys("thinhkhang@gmail.com");
           loginPage.inputPassword().sendKeys("acb123443531");
           loginPage.loginBtnSel().click();

           String alertTitle = loginPage.alert().title().getText();
           Assert.assertTrue(alertTitle.equals("Success"), "[ERR]: Login failed");
           loginPage.alert().okButton().click();
    }
}
