package io.github.mung.drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public DriverManager() {
        super();
    }

    public static WebDriver getDriver() {
        return  driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        if(DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
    }
}
