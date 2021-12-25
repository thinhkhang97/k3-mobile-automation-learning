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
import test_data.authentication.DataObjectBuilder;
import test_data.authentication.LoginCred;

public class LoginPageTest {

    @Test(dataProvider = "loginCredData")
    public void loginWithCorrectCred(LoginCred loginCred) {
        AppiumServer appiumServer = new AppiumServer("127.0.0.1", 8081);
        appiumServer.start();

       try {
           AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory(appiumServer.getInstance().getUrl());
           androidDriverFactory.setImplicitlyWaitTime(2L);
           AppiumDriver<MobileElement> driver = androidDriverFactory.getDriver();

           LoginPage loginPage = new LoginPage(driver);
           BottomNavigationBar bottomNavigationBar = loginPage.bottomNavigationBar();

           bottomNavigationBar.login().click();

           loginPage.inputEmail().sendKeys(loginCred.getEmail());
           loginPage.inputPassword().sendKeys(loginCred.getPassword());
           loginPage.loginBtn().click();

           String alertTitle = loginPage.alert().title().getText();
           Assert.assertTrue(alertTitle.equals("Success"), "[ERR]: Login failed");
           loginPage.alert().okButton().click();

       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           appiumServer.stop();
       }
    }

    @DataProvider
    public LoginCred[] loginCredData() {
        return DataObjectBuilder.buildLoginCreds("/src/main/resources/test-data/valid-login-credential.json");
    }
}
