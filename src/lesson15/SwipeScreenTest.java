package lesson15;

import drivers.AndroidDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import server.AppiumServer;
import utils.SwipeUtil;

import java.time.Duration;
import java.util.List;

public class SwipeScreenTest {
    public static void main(String[] args) {
        AppiumServer appiumServer = new AppiumServer("127.0.0.1", 8081);
        appiumServer.start();

        try {
            AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory(appiumServer.getInstance().getUrl());
            androidDriverFactory.setImplicitlyWaitTime(2L);
            AppiumDriver<MobileElement> driver = androidDriverFactory.getDriver();

            MobileElement swipeLabelElem = driver.findElementByAccessibilityId("Swipe");
            swipeLabelElem.click();

            // Need to wait until the navigation finish
            WebDriverWait wait = new WebDriverWait(driver, 30L);
            wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[contains(@text, 'Swipe horizontal')]")));

            swipeToSpecificCart(driver);
            swipeToFoundMe(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumServer.stop();
        }
    }

    public static void swipeToSpecificCart(MobileDriver driver) {
        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();

        int xStartPoint = width / 2;
        int xEndPoint = width * 10 / 100;
        int yStartPoint = height / 2;
        int yEndPoint = yStartPoint;

        // Make point
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
        By searchElementSel = By.xpath("//*[contains(@text, 'EXTENDABLE')]");

        // Perform touch action
        final int NUMBER_CART_TO_SWIPE = 5;
        SwipeUtil swipeUtil = new SwipeUtil(driver);
        swipeUtil.swipeUntil(searchElementSel, startPoint, endPoint, NUMBER_CART_TO_SWIPE);
    }

    public static void swipeToFoundMe(MobileDriver driver) {
        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();

        int xStartPoint = width / 2;
        int xEndPoint = xStartPoint;
        int yStartPoint = height * 90 / 100;
        int yEndPoint = height * 10 / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
        By searchElementSel = By.xpath("//*[contains(@text, 'You found me!!!')]");

        final int NUMBER_CART_TO_SWIPE = 3;
        SwipeUtil swipeUtil = new SwipeUtil(driver);
        swipeUtil.swipeUntil(searchElementSel, startPoint, endPoint, NUMBER_CART_TO_SWIPE);
    }
}
