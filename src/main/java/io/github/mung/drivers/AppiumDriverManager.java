package io.github.mung.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;


import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;

public final class AppiumDriverManager {

    public AndroidDriver androidDriver;
    private AppiumDriverLocalService localService;
    

    private static final String APP_PATH = String.valueOf(
            Path.of(System.getProperty("user.dir"), "/src/test/resources/app/","android.wdio.native.app.v1.0.8.apk" )
    );

    public void startServer() {
        // Build the Appium service
        AppiumServiceBuilder  builder = new AppiumServiceBuilder();
        builder.withIPAddress("0.0.0.0");
        builder.usingPort(4723);

        // Start the Appium server
        localService = AppiumDriverLocalService.buildService(builder);
        localService.start();
    }

    private static UiAutomator2Options uiAutomator2Options() {
        final UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options()
                .setAvd(System.getProperty("androidDeviceName"))
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setAppPackage(System.getProperty("androidAppPackage"))
                .setAppActivity(System.getProperty("androidAppActivity"))
                .setApp(APP_PATH)
                .setAutoGrantPermissions(true)
                .setNoReset(false);

        return  uiAutomator2Options;
    }

    private HashMap<String, Object> ltOptions() (
            final var ltOptions =  new HashMap<String, Object>();
            )
}
