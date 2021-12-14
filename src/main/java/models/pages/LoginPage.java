package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.BottomNavigationBar;
import org.openqa.selenium.By;

public class LoginPage {
    private final AppiumDriver<MobileElement> driver;
    private final By inputEmailSel = MobileBy.AccessibilityId("input-email");
    private final By inputPasswordSel = MobileBy.AccessibilityId("input-password");
    private final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    private Alert alert;

    private BottomNavigationBar bottomNavigationBar;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public MobileElement inputEmail() {
        return this.driver.findElement(inputEmailSel);
    }

    public MobileElement inputPassword() {
        return this.driver.findElement(inputPasswordSel);
    }

    public MobileElement loginBtnSel() {
        return this.driver.findElement(loginBtnSel);
    }

    public BottomNavigationBar bottomNavigationBar() {
        if (this.bottomNavigationBar == null) {
            this.bottomNavigationBar = new BottomNavigationBar(this.driver);
        }

        return this.bottomNavigationBar;
    }

    public Alert alert() {
        if(alert == null) {
            this.alert = new Alert(this.driver);
        }
        return this.alert;
    }

    public static class Alert {
        private final AppiumDriver<MobileElement> driver;
        private final By titleSel = MobileBy.id("android:id/alertTitle");
        private final By okButtonSel = MobileBy.id("android:id/button1");

        public Alert(AppiumDriver driver) {
            this.driver = driver;
        }

        public MobileElement title() {
            return this.driver.findElement(titleSel);
        }

        public MobileElement okButton() {
            return this.driver.findElement(okButtonSel);
        }
    }
}
