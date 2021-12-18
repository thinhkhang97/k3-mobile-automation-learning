package drivers;

import capabilities.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import server.AppiumServer;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactoryEx {

    AppiumServer appiumServer;

    private Long implicitlyWaitTime = 30L;

    private URL url;

    private String platformName = "Android";

    private String udid = "emulator-5554";

    private String automationName = "uiautomator2";

    private String appPackage = "com.wdiodemoapp";

    private String appActivity = "com.wdiodemoapp.MainActivity";

    AndroidDriver<MobileElement> driver;

    DesiredCapabilities desiredCapabilities;

    public DriverFactoryEx() {
        desiredCapabilities = new DesiredCapabilities();
        appiumServer = new AppiumServer("127.0.0.1", 8081);
    }

    public Long getImplicitlyWaitTime() {
        return implicitlyWaitTime;
    }

    public void setImplicitlyWaitTime(Long implicitlyWaitTime) {
        this.implicitlyWaitTime = implicitlyWaitTime;
    }

    public URL getUrl() {
        return url;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getAutomationName() {
        return automationName;
    }

    public void setAutomationName(String automationName) {
        this.automationName = automationName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    private void init() {
        appiumServer.start();
        this.url = appiumServer.getInstance().getUrl();

        desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, this.platformName);
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, this.udid);
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, this.automationName);
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, this.appPackage);
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, this.appActivity);
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);

        driver = new AndroidDriver<MobileElement>(this.url, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(this.implicitlyWaitTime, TimeUnit.SECONDS);
    }

    public AndroidDriver<MobileElement> getDriver() {
        if (driver == null) {
            init();
        }
        return driver;
    }

    public void quitSession() {
        if(driver!=null) {
            driver.quit();
            driver = null;
            appiumServer.stop();
        }
    }
}
