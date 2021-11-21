package server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;
import lesson16.AndroidServerFlagEx;

import java.io.IOException;

public class AppiumServer {
    private String URL = "127.0.0.1";

    private Integer port = 4723;

    private  AppiumDriverLocalService appiumDriverLocalService;

    public AppiumServer() {
        this.initBuildService();
    }

    public AppiumServer(String URL, Integer port) {
        this.URL = URL;
        this.port = port;
        this.initBuildService();
    }

    public void initBuildService() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withArgument(AndroidServerFlagEx.ALLOW_INSECURE, "chromedriver_autodownload");
        appiumServiceBuilder.withIPAddress(this.URL).usingPort(this.port);
        appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
    }

    public void start() {
        appiumDriverLocalService.start();
    }

    public AppiumDriverLocalService getInstance() {
        return appiumDriverLocalService;
    }

    public void stop() {
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
