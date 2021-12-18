package test;

import drivers.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTest {
    private final static List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactoryEx> driverThread;

    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() {
        // Init a new thread before run test suite to avoid socket hang up
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactoryEx newDriverThread = new DriverFactoryEx();
            driverThreadPool.add(newDriverThread);
            return newDriverThread;
        });
    }

    @AfterSuite(alwaysRun = true)
    public static void afterSuite() {
        for(DriverFactoryEx _driverThread: driverThreadPool) {
            _driverThread.quitSession();
        }
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getDriver();
    }
}
