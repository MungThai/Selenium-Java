package io.github.mung.testcases.web;

import io.github.mung.helpers.ExcelHelpers;
import io.github.mung.listeners.TestListener;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//import static io.github.mung.reports.ExtentTestManager.startTest;


public class LoginTest extends BaseTest {

    @Listeners({ TestListener.class })
    @Epic("Regress Tests")
    @Feature("Login Tests")
    public  class LoginTests extends BaseTest {

       private  ExcelHelpers excel = new ExcelHelpers();

        @Test(priority = 1, description = "Login fail with empty username")
        @Severity(SeverityLevel.BLOCKER)
        @Description("Test Description: Login fail with empty username")
        @Story("Login Test: Login fail with empty username")
        public void TC_loginFailWithEmptyUsername() {
          //  excel.setExcelFile(FrameworkConstants.EXCEL_HRM_LOGIN, "Login");
          //  String password = excel.getCellData(1, "password");
          //  getLoginPage().loginFailWithEmptyusername(" ", excel.getCellData(1, "password"));
            getLoginPage().loginFailWithEmptyUsername(" ","password");
        }

        @Test(priority = 2, description = "Login fail with empty password")
        @Severity(SeverityLevel.NORMAL)
        @Description("Test Description: Login fail with empty password")
        @Story("Login Test: Login fail with empty password")
        public void TC_loginFailWithEmptyPassword() {

            getLoginPage().loginFailWithEmptyPassword("Admin "," ");
        }

        @Test(priority =3, description = "Login fail with invalid username")
        @Severity(SeverityLevel.NORMAL)
        @Description("Test Description: Login test with invalid username")
        @Story("Login Test: Login fail with invalid username")
        public void TC_loginFailWithInvalidUsername() {
            //     startTest(method.getName(), "Invalid Login Scenario with invalid username and password");
        //    ExcelHelpers excel = new ExcelHelpers();
       //     excel.setExcelFile(FrameworkConstants.EXCEL_HRM_LOGIN, "Login");
       //     getLoginPage().loginFailWithInvalidusername(excel.getCellData(1, "username"), excel.getCellData(1, "password"));
            getLoginPage().loginFailWithInvalidUsername("QA", "admin123");

        }

        @Test(priority =4, description = "Login fail with invalid password")
        @Severity(SeverityLevel.NORMAL)
        @Description("Test Description: Login fail test with password")
        @Story("Login Test: Login fail with invalid password")
        public void TC_loginFailWithInvalidPassword() {
            //     startTest(method.getName(), "Invalid Login Scenario with invalid username and password");
            //    ExcelHelpers excel = new ExcelHelpers();
         //   excel.setExcelFile(FrameworkConstants.EXCEL_HRM_LOGIN, "Login");
         //   getLoginPage().loginFailWithInvalidPassword(excel.getCellData(1, "mail"), excel.getCellData(1, "password"));
            getLoginPage().loginFailWithInvalidPassword("Admin", "invalidPassword");
        }

        @Test(priority = 5, description = "Login success.")
        @Severity(SeverityLevel.CRITICAL)
        @Description("Test Description: Login success")
        @Story("Login Test: Login Success")
        public void TC_loginSuccess() {
         //   startTest(method.getName(), "Invalid Login Scenario with empty username and password.");
       //     excel.setExcelFile(FrameworkConstants.EXCEL_HRM_LOGIN, "Login");
       //     getLoginPage().loginSuccess(excel.getCellData(6, "username"), excel.getCellData(6, "password") );
            getLoginPage().login("Admin", "admin123");

        }

    }

}

