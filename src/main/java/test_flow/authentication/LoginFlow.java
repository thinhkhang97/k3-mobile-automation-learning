package test_flow.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.BottomNavigationBar;
import models.pages.LoginPage;
import org.testng.Assert;
import test_data.authentication.LoginCred;

public class LoginFlow {

    private final AppiumDriver<MobileElement> appiumDriver;
    private LoginPage loginPage;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    private void initLoginPage() {
        loginPage = new LoginPage(appiumDriver);
    }

    public LoginFlow navigateToLoginForm() {
        if(loginPage == null) {
            initLoginPage();
        }

        BottomNavigationBar bottomNavigationBar = loginPage.bottomNavigationBar();
        bottomNavigationBar.login().click();
        return this;
    }

    @Step("Login with email: {loginCred.email} and password: {loginCred.password}")
    public LoginFlow login(LoginCred loginCred) {
        if(loginPage == null) {
            throw new RuntimeException("Please use navigate to login form method first");
        }
        loginPage
                .inputEmail(loginCred.getEmail())
                .inputPassword(loginCred.getPassword())
                .clickLoginButton();

        return this;
    }

    public void verifyLoginSuccess() {
        String alertTitle = loginPage.alert().title().getText();
        Assert.assertTrue(alertTitle.equals("Success"), "[ERR]: Login failed");
        loginPage.alert().okButton().click();
    }

    public void verifyLoginFail() {

    }
}
