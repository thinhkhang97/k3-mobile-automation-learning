package test;

import drivers.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {
    private final static List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactoryEx> driverThread;
    private String udid;
    private String port;
    private String systemPort;


    @BeforeTest(alwaysRun = true)
    @Parameters({"udid", "port", "systemPort"})
    public void beforeTest(String udid, String port, String systemPort) {
        this.udid = udid;
        this.port = port;
        this.systemPort = systemPort;
        // Init a new thread before run test suite to avoid socket hang up
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactoryEx newDriverThread = new DriverFactoryEx();
            driverThreadPool.add(newDriverThread);
            return newDriverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        for (DriverFactoryEx _driverThread : driverThreadPool) {
            _driverThread.quitSession();
        }
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String testMethodName = result.getName();

            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            String dateTaken = y + "-" + (m + 1) + "-" + d + "-" + hr + "-" + min + "-" + second;
            String fileLocation = System.getProperty("user.dir") + "/screenshot/"+testMethodName + "_" + dateTaken + ".png";

            File screenShot = driverThread.get().getDriver().getScreenshotAs(OutputType.FILE);

            try{
                FileUtils.copyFile(screenShot, new File(fileLocation));
                Path content = Paths.get(fileLocation);
                try(InputStream is = Files.newInputStream(content)) {
                    Allure.addAttachment(testMethodName, is);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getDriver(this.udid, this.port, this.systemPort);
    }
}
