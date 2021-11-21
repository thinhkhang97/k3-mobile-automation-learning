package models.components;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavigationBar {
    private final AppiumDriver<MobileElement> driver;

    private final By webViewSel = MobileBy.AccessibilityId("WebView");
    private final By loginSel = MobileBy.AccessibilityId("Login");
    private final By formSel = MobileBy.AccessibilityId("Forms");
    private final By swipeSel = MobileBy.AccessibilityId("Swipe");

    public BottomNavigationBar(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public MobileElement webView() {
        return this.driver.findElement(webViewSel);
    }

    public MobileElement login() {
        return this.driver.findElement(loginSel);
    }

    public MobileElement form() {
        return this.driver.findElement(formSel);
    }

    public MobileElement swipe() {
        return this.driver.findElement(swipeSel);
    }
}
