package lesson13;

import capabilities.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Launch {
    public static void main(String[] args) {


        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingPort(4723);

        AppiumDriverLocalService appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumDriverLocalService.start();

        AppiumDriver<MobileElement> appiumDriver = null;

       try {
           DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
           desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
           desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "emulator-5554");
           desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
           desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
           desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
           desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);

           appiumDriver = new AppiumDriver<MobileElement>(appiumDriverLocalService.getUrl(), desiredCapabilities);
       } catch (Exception error) {

       }

        if(appiumDriver != null ) {
            appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
            System.out.println("Connected to appium server!");
            MobileElement loginBtn = appiumDriver.findElementByAccessibilityId("Login");
            loginBtn.click();

        } else {
            System.out.println("[ERR] Couldn't connect to appium server");
        }

        appiumDriverLocalService.stop();

        // Kill all task using port
        String killNodeWindows = "taskkill /F /IM node.exe";
        String killNodeLinux = "killall node";
        String killNodeCmd = System.getProperty("os.name").toLowerCase().startsWith("windows") ? killNodeWindows : killNodeLinux;

        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(killNodeCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
