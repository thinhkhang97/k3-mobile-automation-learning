package utils;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

public class SwipeUtil {
    MobileDriver driver;

    public SwipeUtil(MobileDriver driver) {
        this.driver = driver;
    }

    public void swipeUntil(By searchElementSel, PointOption startPoint, PointOption endPoint, int maxTimesSwipe) {
        TouchAction touchAction = new TouchAction(driver);
        boolean endCondition = false;
        int count = 0;
        while (!endCondition) {
            List<MobileElement> foundElements = driver.findElements(searchElementSel);
            if (foundElements.size() > 0) {
                break;
            }
            touchAction.press(startPoint).
                    waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).
                    moveTo(endPoint).
                    release().
                    perform();
            count++;
            if (count > maxTimesSwipe) {
                endCondition = true;
            }
        }
    }
}
