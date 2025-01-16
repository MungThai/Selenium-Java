package io.github.mung.drivers;

import io.github.mung.constants.GlobalVars;
import io.github.mung.enums.Target;
import io.github.mung.exceptions.TargetNotValidException;
import io.github.mung.utils.LogUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class TargetFactory {

    public WebDriver createInstance() {
        Target target = Target.valueOf(GlobalVars.TARGET.toUpperCase());
        WebDriver webdriver;

        switch (target) {
            case LOCAL:
                //Create new driver from Enum setup in BrowserFactory class
                webdriver = BrowserFactory.valueOf(GlobalVars.BROWSER.toUpperCase()).createDriver();
                break;
            case REMOTE:
                //Create new driver on Cloud (Selenium Grid, Docker) from method below
                webdriver = createRemoteInstance(BrowserFactory.valueOf(GlobalVars.BROWSER.toUpperCase()).getOptions());
                break;
            default:
                throw new TargetNotValidException(target.toString());
        }
        return webdriver;
    }

    public WebDriver createInstance(String browser) {
        Target target = Target.valueOf(GlobalVars.TARGET.toUpperCase());
        WebDriver webdriver;

        String browserName = (GlobalVars.BROWSER != null && !GlobalVars.BROWSER.isEmpty()) ? GlobalVars.BROWSER
                : browser;

        Allure.step("\uD83E\uDD16 Run on browser: " + browserName);

        switch (target) {
            case LOCAL:
                //Create new driver from Enum setup in BrowserFactory class
                webdriver = BrowserFactory.valueOf(browserName.toUpperCase()).createDriver();
                break;
            case REMOTE:
                //Create new driver on Cloud (Selenium Grid, Docker) from method below
                webdriver = createRemoteInstance(BrowserFactory.valueOf(browserName.toUpperCase()).getOptions());
                break;
            default:
                throw new TargetNotValidException(target.toString());
        }
        return webdriver;
    }

    private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            String gridURL = String.format("http://%s:%s", GlobalVars.REMOTE_URL, GlobalVars.REMOTE_PORT);
            LogUtils.info("Remote URL: " + gridURL);
            remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
        } catch (java.net.MalformedURLException e) {
            LogUtils.error("Grid URL is invalid or Grid Port is not available");
            LogUtils.error(String.format("Browser: %s", capability.getBrowserName()), e);
        } catch (IllegalArgumentException e) {
            LogUtils.error(String.format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
        }

        return remoteWebDriver;
    }
}
