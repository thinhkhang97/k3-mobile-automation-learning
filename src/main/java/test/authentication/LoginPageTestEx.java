package test.authentication;

import drivers.AndroidDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import models.components.BottomNavigationBar;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import server.AppiumServer;
import test.BaseTest;
import test_data.authentication.DataObjectBuilder;
import test_data.authentication.LoginCred;
import test_flow.authentication.LoginFlow;

public class LoginPageTestEx extends BaseTest {

    @TmsLink("IP-1009")
    @Description("Test login with valid data driven.")
    @Test(dataProvider = "loginWithValidCredData", priority = 1)
    public void loginWithCorrectCred(LoginCred loginCred) {
        AppiumDriver<MobileElement> driver = getDriver();
        LoginFlow loginFlow = new LoginFlow(driver);
        loginFlow.navigateToLoginForm().login(loginCred).verifyLoginSuccess();
    }

    @TmsLink("IP-1010")
    @Description("Test login with invalid data driven.")
    @Test(dataProvider = "loginWithInvalidCredData", priority = 2)
    public void loginWithInCorrectCred(LoginCred loginCred) {
        AppiumDriver<MobileElement> driver = getDriver();
        LoginFlow loginFlow = new LoginFlow(driver);
        loginFlow.navigateToLoginForm().login(loginCred).verifyLoginFail();
    }


    @DataProvider
    public LoginCred[] loginWithValidCredData() {
        return DataObjectBuilder.buildLoginCreds("/src/main/resources/test-data/authentication/valid-login-credential.json");
    }

    @DataProvider
    public LoginCred[] loginWithInvalidCredData() {
        return DataObjectBuilder.buildLoginCreds("/src/main/resources/test-data/authentication/invalid-login-credential.json");
    }
}
