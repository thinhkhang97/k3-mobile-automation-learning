package oldlessons.lesson17;

import drivers.AndroidDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import server.AppiumServer;

import java.time.Duration;
import java.util.List;

public class SwipeToOpenNotification {
    public static void main(String[] args) {
        AppiumServer appiumServer = new AppiumServer("127.0.0.1", 8081);
        appiumServer.start();

        try {
            AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory(appiumServer.getInstance().getUrl());
            androidDriverFactory.setImplicitlyWaitTime(2L);
            AppiumDriver<MobileElement> driver = androidDriverFactory.getDriver();

            // Get screen size
            Dimension dimension = driver.manage().window().getSize();
            int width = dimension.getWidth();
            int height = dimension.getHeight();

            int xStartPoint = width / 2;
            int xEndPoint = xStartPoint;
            int yStartPoint = 0;
            int yEndPoint = height * 6 / 10;

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

            List<MobileElement> notificationElemList = driver.findElements(By.id("com.android.systemui:id/expanded"));
            notificationElemList.forEach(notificationItem -> {
                MobileElement titleElem = notificationItem.findElement(By.id("com.android.systemui:id/notification_title"));
                MobileElement textElem = notificationItem.findElement(By.id("com.android.systemui:id/notification_text"));
                System.out.println(titleElem.getText());
                System.out.println(textElem.getText());
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumServer.stop();
        }
    }
}
