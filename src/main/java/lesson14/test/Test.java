package lesson14.test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import drivers.AndroidDriverFactory;
import server.AppiumServer;

public class Test {
    public static void main(String[] args) {
        AppiumServer appiumServer = new AppiumServer("127.0.0.1", 8081);
        appiumServer.start();

        try {
            AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory(appiumServer.getInstance().getUrl());
            AndroidDriver<MobileElement> driver = androidDriverFactory.getDriver();

            MobileElement loginBtn = driver.findElementByAccessibilityId("Login");
            loginBtn.click();

            MobileElement inputEmailElem = driver.findElementByXPath("//android.widget.EditText[@content-desc=\"input-email\"]");
            MobileElement inputPasswordElem = driver.findElementByXPath("//android.widget.EditText[@content-desc=\"input-password\"]");

            inputEmailElem.sendKeys("thinkhang97@gmail.com");
            inputPasswordElem.sendKeys("123454555");

            MobileElement loginNote = driver.findElementByXPath("//*[contains(@text, 'When the device')]");
            System.out.println("NOTE: " + loginNote.getText());

            MobileElement submitLoginBtn = driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"button-LOGIN\"]/android.view.ViewGroup");
            submitLoginBtn.click();
        } finally {
            appiumServer.stop();
        }
    }
}
