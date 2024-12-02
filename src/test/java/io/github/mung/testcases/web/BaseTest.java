package io.github.mung.testcases.web;

import io.github.mung.drivers.DriverManager;
import io.github.mung.drivers.TargetFactory;
import io.github.mung.helpers.PropertiesHelpers;
import io.github.mung.pages.BasePage;
import io.github.mung.utils.CommonPageUtils;
import io.github.mung.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class BaseTest extends BasePage {

    @Parameters("BROWSER")
    @BeforeMethod
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        CommonPageUtils.stopSoftAssertAll();
        DriverManager.quit();
    }

    public WebDriver createBrowser(@Optional("chrome") String browser) {
        PropertiesHelpers.loadAllFiles();
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
        return DriverManager.getDriver();
    }
}