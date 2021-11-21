package test.authentication;

import drivers.AndroidDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.BottomNavigationBar;
import models.pages.LoginPage;
import server.AppiumServer;

public class LoginPageTest {
    public static void main(String[] args) {
        AppiumServer appiumServer = new AppiumServer("127.0.0.1", 8081);
        appiumServer.start();

       try {
           AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory(appiumServer.getInstance().getUrl());
           androidDriverFactory.setImplicitlyWaitTime(2L);
           AppiumDriver<MobileElement> driver = androidDriverFactory.getDriver();

           LoginPage loginPage = new LoginPage(driver);
           BottomNavigationBar bottomNavigationBar = loginPage.bottomNavigationBar();

           bottomNavigationBar.login().click();

           loginPage.inputEmail().sendKeys("thinhkhang123@gmail.com");
           loginPage.inputPassword().sendKeys("abcas234");
           loginPage.loginBtnSel().click();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           appiumServer.stop();
       }

    }
}
