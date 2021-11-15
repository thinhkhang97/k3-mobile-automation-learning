package drivers;

import capabilities.MobileCapabilityTypeEx;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidDriverFactory {

    private Long implicitlyWaitTime = 30L;

    private URL url;

    private String platformName = "Android";

    private String udid = "emulator-5554";

    private String automationName = "uiautomator2";

    private String appPackage = "com.wdiodemoapp";

    private String appActivity = "com.wdiodemoapp.MainActivity";

    AndroidDriver<MobileElement> driver;

    DesiredCapabilities desiredCapabilities;

    public AndroidDriverFactory(URL url) {
        this.url = url;
        desiredCapabilities = new DesiredCapabilities();
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

    public void setUrl(URL url) {
        this.url = url;
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

    public io.appium.java_client.android.AndroidDriver<MobileElement> getDriver() {
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, this.platformName);
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, this.udid);
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, this.automationName);
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, this.appPackage);
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, this.appActivity);
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);

        driver = new AndroidDriver<MobileElement>(this.url, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(this.implicitlyWaitTime, TimeUnit.SECONDS);

        return driver;
    }
}
