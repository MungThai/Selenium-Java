package io.github.mung.pages;


import io.github.mung.constants.FrameworkConstants;
import io.github.mung.utils.LogUtils;
import org.openqa.selenium.By;

import static io.github.mung.utils.CommonPageUtils.*;
import static org.testng.Assert.assertEquals;

public class LoginPage extends BasePage {

    private By usernmeField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By usernameFieldRequired = By.xpath("//input[@name='username']/../../span");
    private By passwordFieldRequired = By.xpath("//input[@name='password']/../../span");
    private By invalidCredentialError = By.xpath("//p[contains(@class, 'oxd-alert-content-text')]");

    public void openLoginPage() {
        openWebsite(FrameworkConstants.URL_HRM);
    }

    public void login(String username, String password) {
        openWebsite(FrameworkConstants.URL_HRM);
        setText(usernmeField, username);
        setText(passwordField, password);
        clickElement(loginButton);

        waitForPageLoaded();
    }

    public void loginFailWithEmptyUsername(String username, String password) {
        LogUtils.info("Login failed with empty username");
        login(username, password);
        clickElement(loginButton);
        waitForPageLoaded();
        verifyEquals(getTextElement(usernameFieldRequired), "Required");
    }

    public void loginFailWithEmptyPassword(String username, String password) {
        LogUtils.info("Login failed with empty password");
        login(username, password);
        clickElement(loginButton);
        waitForPageLoaded();
        verifyEquals(getTextElement(passwordFieldRequired), "Required");
    }


    public void loginFailWithInvalidUsername(String username,String password)  {
        LogUtils.info("Login failed  with invalid username");
        login(username, password);
        assertEquals(getTextElement(invalidCredentialError), "Invalid credentials");
    }

    public void loginFailWithInvalidPassword(String username,String password)  {
        LogUtils.info("Login failed with invalid password");
        login(username, password);
        assertEquals(getTextElement(invalidCredentialError), "Invalid credentials");
    }

    public void loginSuccess(String username, String  password) {
        LogUtils.info("Login success");
        login(username, password);
    }
}
