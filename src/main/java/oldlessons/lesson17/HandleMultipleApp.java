package oldlessons.lesson17;

import drivers.AndroidDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import server.AppiumServer;

import java.time.Duration;

public class HandleMultipleApp {
    public static void main(String[] args) {
        AppiumServer appiumServer = new AppiumServer("127.0.0.1", 8081);
        appiumServer.start();

        try {
            AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory(appiumServer.getInstance().getUrl());
            androidDriverFactory.setImplicitlyWaitTime(2L);
            AppiumDriver<MobileElement> driver = androidDriverFactory.getDriver();

            MobileElement formLabelElem = driver.findElementByAccessibilityId("Login");
            formLabelElem.click();

            driver.runAppInBackground(Duration.ofSeconds(-1));

            driver.activateApp("com.android.settings");

            MobileElement wifiElem = driver.findElementByXPath("//*[contains(@text, 'Network')]");
            wifiElem.click();

            MobileElement wifiToggleElem = driver.findElementByXPath( "//android.widget.Switch[@content-desc=\"Wi‑Fi\"]");
            wifiToggleElem.click();

            driver.runAppInBackground(Duration.ofSeconds(-1));

            driver.activateApp("com.wdiodemoapp");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumServer.stop();
        }
    }
}
