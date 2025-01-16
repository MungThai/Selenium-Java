package io.github.mung.testcases.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.github.mung.drivers.AppiumDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseTest {

   public AppiumDriver driver;
   private AppiumDriverLocalService service;
   private UiAutomator2Options option;


    @BeforeSuite
    public void startAppiumServer() {
       // Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();

        builder.withIPAddress("0.0.0.0");
        builder.usingPort(4723);

        // Start the appium server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    @BeforeClass
    public void runApplication() {
        option = new UiAutomator2Options();
        option.setDeviceName(System.getProperty("ANDROID_DEVICE_NAME"));
    }
}
