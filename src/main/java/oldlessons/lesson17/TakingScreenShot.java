package oldlessons.lesson17;

import drivers.AndroidDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import server.AppiumServer;

import java.io.File;

public class TakingScreenShot {
    public static void main(String[] args) {
        AppiumServer appiumServer = new AppiumServer("127.0.0.1", 8081);
        appiumServer.start();

        try {
            AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory(appiumServer.getInstance().getUrl());
            androidDriverFactory.setImplicitlyWaitTime(2L);
            AppiumDriver<MobileElement> driver = androidDriverFactory.getDriver();

            MobileElement formLabelElem = driver.findElementByAccessibilityId("Login");
            formLabelElem.click();

            WebDriverWait wait = new WebDriverWait(driver, 5L);
            wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[contains(@text, 'Login')]")));

            File base64ScreenShotData = driver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginForm.png");

            FileUtils.copyFile(base64ScreenShotData, new File(fileLocation));

            MobileElement loginButtonElem = driver.findElementByAccessibilityId("button-LOGIN");
            File base64LoginButtonShotData = loginButtonElem.getScreenshotAs(OutputType.FILE);
            String loginButtonElemFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("buttonLoginForm.png");

            FileUtils.copyFile(base64LoginButtonShotData, new File(loginButtonElemFileLocation));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumServer.stop();
        }
    }
}
