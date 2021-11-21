package oldlessons.lesson16;

import drivers.AndroidDriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import server.AppiumServer;

import java.util.ArrayList;
import java.util.List;

public class HandleHybrid {
    public static void main(String[] args) {
        AppiumServer appiumServer = new AppiumServer("127.0.0.1", 8081);
        appiumServer.start();

        try {
            AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory(appiumServer.getInstance().getUrl());
            AndroidDriver<MobileElement> driver = androidDriverFactory.getDriver();

            MobileElement webView = driver.findElementByAccessibilityId("Webview");
            webView.click();

            driver.getContextHandles().forEach(context -> System.out.println(context));

            driver.context("WEBVIEW_com.wdiodemoapp");

            WebElement menuBtn = driver.findElementByCssSelector(".navbar__toggle");
            menuBtn.click();

            List<MobileElement> menuListElem = driver.findElementsByCssSelector(".menu__list-item a");
            List<MenuItem> menuItems = new ArrayList<>();
            menuListElem.forEach(menuItemElem -> {
               String name = menuItemElem.getText();
               if(name.isEmpty()) {
                   name = "GitHub";
               }
               menuItems.add(new MenuItem(name, menuItemElem.getAttribute("href")));
            });

            menuItems.forEach(item -> System.out.println(item.getHref()));

            driver.context("NATIVE_APP");
            MobileElement loginBtnElem = driver.findElementByAccessibilityId("Login");
            loginBtnElem.click();

        } finally {
            appiumServer.stop();
        }
    }

    public static class MenuItem {
        private String name;

        private String href;

        public MenuItem(String name, String href) {
            this.name = name;
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
