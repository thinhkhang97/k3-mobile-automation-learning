package oldlessons.lesson15;

import drivers.AndroidDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import server.AppiumServer;

import java.time.Duration;

public class FormScreenTest {
    public static void main(String[] args) {
        AppiumServer appiumServer = new AppiumServer("127.0.0.1", 4725);
        appiumServer.start();

        try {
            AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory(appiumServer.getInstance().getUrl());
            AppiumDriver<MobileElement> driver = androidDriverFactory.getDriver();

            MobileElement formLabelElem = driver.findElementByAccessibilityId("Forms");
            formLabelElem.click();

            // Need to wait until the navigation finish
            WebDriverWait wait = new WebDriverWait(driver, 30L);
            wait.until(ExpectedConditions.visibilityOf(driver.findElementByAccessibilityId("switch")));

            // Get screen size
            Dimension dimension = driver.manage().window().getSize();
            int width = dimension.getWidth();
            int height = dimension.getHeight();

            int xStartPoint = width / 2;
            int xEndPoint = xStartPoint;
            int yStartPoint = height / 2;
            int yEndPoint = 0;

            // Make point
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            // Perform touch action
            TouchAction touchAction = new TouchAction(driver);
            // Swipe up, need to wait a bit before move to end point
            touchAction.press(startPoint).
                    waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).
                    moveTo(endPoint).
                    release().
                    perform();

            MobileElement activeBtnElem = driver.findElementByAccessibilityId("button-Active");
            activeBtnElem.click();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumServer.stop();
        }
    }
}
