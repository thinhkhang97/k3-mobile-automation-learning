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
        if(this.bottomNavigationBar == null) {
            this.bottomNavigationBar = new BottomNavigationBar(this.driver);
        }

        return this.bottomNavigationBar;
    }
}
